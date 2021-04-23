package com.huawei.cloudate.spectro;

import com.huawei.cloudate.spectro.exception.SpectroExcetion;
import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;


public class UprMeter implements SpectroStrategy{

    private int mMKLIB_JAVA_VER = 0x00000103;  // 0.0.1.3, version of java sdk
    private long mVersion = 0;  // version of dll

    /**
     * meter parameters, use to pr-set meter
     */
    private int MAX_TIMEOUT = 5000;
    private int mCalCh = 1;
    private int mCalChSpdRatio = mCalCh;
    private int mCalChSpdWls = 0;
    private int mCalChCMCh = 0;
    private float mSyncFreq = 60;
    //private int mMaxExp = 5000000;   // 5s
    private int mMaxExp = 1000000;   // 1s
    //private int mMaxExp = 500000;   // 500ms
    private int mCycNum = 1;
    private int mSpdLen = 401;

    static int mIsInit = 0;

    /**
     * uprtek meter information get form meter
     */
    private String mDevName;
    private String mOptSN;
    private String mErrMsg;
    private String mHWVer;
    private String mFWVer;
    private int mDevId = -1;
    private UprMeterMgr mMgr;

    /**
     * color data, lv, x, y, and spectrum.
     */
    public float mValueLv;
    public float mValuex;
    public float mValuey;
    public float mValueup;
    public float mValuevp;
    public float mValueduv;
    public float mValueCCT;
    public float mValueExpTime;
    public float mValueTackTime;
    public float [] mValueSpd;
    public double [] mValueSpd_double;
    public float [] mValuewavelength;

    public UprMeter(){
        mDevId = -1;
    }
    public void setmDevId(int id, String sn, String name){
        mDevId = id;
        mOptSN = sn;
        mDevName = name;
    }
    @Override
    public boolean init(String sn) throws SpectroExcetion {
        if(mDevId < 0){
            if(!openDevBySN(sn)) {
                String s = String.format("Open device failed. %s", sn);
                throw new SpectroExcetion(s);
            }
        }
        if(!msr_Dark(mDevId)) {
            System.out.printf("%s", mErrMsg);
            throw new SpectroExcetion(mErrMsg);
        }
        mValuewavelength = new float[401];
        mValueSpd = new float [401];
        mValueSpd_double = new double[401];
        return true;
    }

    @Override
    public double[] measure() throws SpectroExcetion {

        boolean result = msr_Capture(mDevId, 1);
        if(result){
            getSpdFromDevice(mDevId);
            for(int i=0; i<mSpdLen; i++) {
                mValueSpd_double[i] = (double) mValueSpd[i];
            }
        }else{
            for(int i=0; i<mSpdLen; i++)
                mValueSpd_double[i] = 0;
        }
        return mValueSpd_double;
    }

    @Override
    public String export(String path) throws SpectroExcetion {
        saveSpdToFile(path);
        return path;
    }

    public boolean openDevBySN(String sn){
        Integer id = -1;
        byte [] optsn = stringToCChar(sn);  // transfer string to C string, end by 0.
        // travsel, get device list by name, and OptSN
        //ArrayList <String> namelist = getDevSNList();
        //ArrayList <String> optsnlist = getOptSNList(namelist);

        // close device if device opened,
        if(mDevId >= 0) {
            MkLib.Instance.mk_CloseSpDev(mDevId);
            mDevId = -1;
        }

        // get total device numbers
        //if(mklib.Instance.mk_GetDeviceCnt() < 1)
        //    return false;

        // get ready and unsed device numbers.
        if(MkLib.Instance.mk_GetDeviceUnusedCnt() < 1)
            return false;

        //id = mklib.Instance.mk_OpenSpDev(stringToCChar(namelist.get(0)));
        //id = mklib.Instance.mk_OpenSpDev_OptSn(stringToCChar(optsnlist.get(0)));
        // open device by OptSN
        id = MkLib.Instance.mk_OpenSpDev_OptSn(optsn);
        if(id>=0) {
            byte [] tmp= new byte[256];
            IntByReference errcode = new IntByReference();

            MkLib.Instance.mk_Msr_GetErrMsg(id, errcode, tmp);
            if(errcode.getValue()<0){
                mErrMsg = String.format("Error code :%d, %s\n", errcode.getValue(), new String(tmp));
                return false;
            }
            MkLib.Instance.mk_Info_Get(id, MkLib.INFO_HWVER, tmp);
            mHWVer = new String(tmp);
            MkLib.Instance.mk_Info_Get(id, MkLib.INFO_FWVER, tmp);
            mFWVer = new String(tmp);
            mDevId = id;
            mOptSN = new String(sn);
            mDevName = "no used";
            return true;
        }
        return false;
    }

    private byte[] stringToCChar(String str){
        byte [] name2 = str.getBytes();
        byte [] name = new byte[name2.length+1];
        for(int i=0; i<name2.length; i++){
            name[i] = name2[i];
        }
        return name;
    }

    private int getByteCharterLen(byte[] aa){
        int len = 0;
        for(int i=0; i<aa.length; i++){
            if(aa[i] != 0)
                len++;
        }
        return len;
    }

    private boolean msr_Dark(Integer id){
        boolean result = true;
        byte tmp[] = new byte[256];
        IntByReference errcode = new IntByReference();

        result &= MkLib.Instance.mk_Msr_AutoDarkCtrl(id, 0); // 0:trun off auto dark, 1: trun on auto dark
        result &= MkLib.Instance.mk_Msr_SetExpMode(id, 0);  // 0 : fast mode, 1:normal mode
        result &= MkLib.Instance.mk_Msr_SetMaxExpTime(id, mMaxExp);   // unit : usec

        System.out.printf("Dark Calibration Start....\n");
        //result = mklib.Instance.mk_senr_Msr_DarkEx(id, 1, 1);    // dark calibration(zero current calibration)
        result = MkLib.Instance.mk_senr_Msr_Dark(id);    // dark calibration(zero current calibration)
        if(!result) {

            MkLib.Instance.mk_Msr_GetErrMsg(id, errcode, tmp);
            if(errcode.getValue()<0){
                mErrMsg = String.format("Error code :%d, %s\n", errcode.getValue(), new String(tmp));
            }
        }
        return result;

    }

    private boolean msr_Capture(Integer id, int times) {
        boolean result = true;
        byte err[] = new byte[256];

        result &= MkLib.Instance.mk_Msr_SetLightMode(id, 1, (int)mSyncFreq);
        //result &= mklib.Instance.mk_Msr_SetLightMode_AutoExp(id, 0);	// AutoExp detection method, 0 : fast, 1: Normal
        result &= MkLib.Instance.mk_Msr_SetLightMode_PreScanPara(id, 1, (float) 0.3); // 1 is mean 1cycle time, 0.3 * full intensity
        result &= MkLib.Instance.mk_Msr_SetLightModeFlt(id, 1, mSyncFreq);
        result &= MkLib.Instance.mk_Msr_SetLightMode_CycleNumber(id, mCycNum);    //
        result &= MkLib.Instance.mk_Msr_SetBoxcarFilter(id, 1, 1, 3);
        // set calibration channel
        result &= MkLib.Instance.mk_Msr_SetSpdRatioCH(id, mCalChSpdRatio);
        result &= MkLib.Instance.mk_Msr_SetSpdWLSCH(id, mCalChSpdWls);
        result &= MkLib.Instance.mk_Msr_SetCMCH(id, mCalChCMCh);

        for(int j=0; j<times; j++) {
            result = MkLib.Instance.mk_senr_Msr_Capture(id, 1, 16666);  // 1 is auto exposure mode, 16666 for manual mode to set exposure time
            if (result) {
                FloatByReference data = new FloatByReference();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_LV, data);
                mValueLv = data.getValue();
                if(mValueLv<10) {
                    result = MkLib.Instance.mk_GetData(id, MkLib.DATA_ExpTime, data);
                    mValueExpTime = data.getValue();
                    result = MkLib.Instance.mk_senr_Msr_CaptureAvg(id, 0, (int)mValueExpTime, 3);
                }
            }

            if (result) {
                String tmp;
                FloatByReference data = new FloatByReference();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_LV, data);
                mValueLv = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_CIE1931_x, data);
                mValuex = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_CIE1931_y, data);
                mValuey = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_CCT, data);
                mValueCCT = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_CIE_duv, data);
                mValueduv = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_ExpTime, data);
                mValueExpTime = data.getValue();
                result = MkLib.Instance.mk_GetData(id, MkLib.DATA_TackTime, data);
                mValueTackTime = data.getValue();
            }
            // save last Spd to File
            // save spd to file
        }
        return result;
    }

    //get spd form device
    private void getSpdFromDevice(Integer id){
        //FloatByReference fr = new FloatByReference();
        Pointer ptr = new Memory(mSpdLen*Native.getNativeSize(Float.TYPE));
        MkLib.Instance.mk_GetSpectrum(id, 380, 780, ptr);
        float [] data = ptr.getFloatArray(0, mSpdLen);
        mValueSpd = Arrays.copyOf(data, mSpdLen);
        for(int j=0; j<mSpdLen; j++)
            mValuewavelength[j] = 380 + j;
    }

    private void saveSpdToFile(String file){
        int len = mValuewavelength.length;
        File f = new File(file);

        if(f.isDirectory()) {
            mErrMsg = "path is not a file";
            return;
        }

        if(!f.exists()){
            // write title
            StringBuilder tsb = new StringBuilder();
            tsb.append("SN,sx,sy,slv,time,exp,");
            for(int i=0; i<len; i++) {
                String str = String.format("%f,", mValuewavelength[i]);
                tsb.append(str);
            }
            tsb.append("\n");
            try {
                BufferedWriter writer = null;
                writer = new BufferedWriter(new FileWriter(file));
                writer.write(tsb.toString());
                writer.close();
            }catch (IOException e){

            }
        }

        StringBuilder sb = new StringBuilder();

        String v = String.format("%s,%5.4f,%5.4f,%5.4f,%f,%d,", mOptSN, mValuex, mValuey, mValueLv, mValueTackTime, (int) mValueExpTime);
        sb.append(v);
        for(int i=0; i<len; i++) {
            String str = String.format("%f,",mValueSpd[i]);
            sb.append(str);
        }
        sb.append("\n");

        try {
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(sb.toString());
            writer.close();
        }
        catch(IOException e){

        }

    }
}
