package com.huawei.cloudate.spectro;

import com.huawei.cloudate.spectro.exception.SpectroExcetion;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.InterruptedByTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class UprMeterMgr {
    private int mMKLIB_JAVA_VER = 0x00000102;  // 0.0.1.2, version of java sdk
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
    private int mMaxExp = 5000000;   // 5s
    private int mCycNum = 1;
    private int mSpdLen = 401;
    static int mIsInit = 0;

    public ArrayList<UprMeter> mDevList;

    public UprMeterMgr(){
        mDevList = new ArrayList<>(16);
        init();
    }

    public boolean isUpdate() {
        return MkLib.Instance.mk_IsUpdate();
    }

    public int getDevNum(){
        return MkLib.Instance.mk_GetNumberOfDev();
    }

    public boolean init() {

        if(!initMKUSB())
            return false;

        mVersion = MkLib.Instance.mk_version();

        return true;
    }

    private boolean initMKUSB(){
        boolean result = true;
        if(mIsInit == 0) {
            MkLib.Instance.mk_Init(1, 300);
            mIsInit = 1;
        }
        System.out.printf("dll version 0x%08x\n", MkLib.Instance.mk_version());
        return result;
    }

    public UprMeter openDev(String sn){
        UprMeter meter = new UprMeter();
        if(meter.openDevBySN(sn) == true){
            mDevList.add(meter);
            return meter;
        }else{
            return null;
        }
    }


    private byte[] stringToCChar(String str){
        byte [] name2 = str.getBytes();
        byte [] name = new byte[name2.length+1];
        for(int i=0; i<name2.length; i++){
            name[i] = name2[i];
        }
        return name;
    }

    private boolean isDevExist(ArrayList<String> l, String sn) {
        if(l.size() ==0)
            return false;
        for (String item:l) {
            String itemsn = getOptSNbyName(item);
            if(itemsn.compareTo(sn)==0) {
                return true;
            }
        }
        return false;
    }

    // get device SN list, the sn equal usb serial number.
    public ArrayList<String> getDevSNList(){
        boolean result;
        byte [] name = new byte[64];
        ArrayList<String> list = new ArrayList<>();
        result = MkLib.Instance.mk_FindFirst(name);
        while(result){
            int len = getByteCharterLen(name);
            String tmp = new String(name, 0, len);
            list.add(tmp + "\0");
            result = MkLib.Instance.mk_FindNext(name);
        }
        return list;
    }

    // get device list by OptSN.
    public ArrayList<String> getOptSNList(ArrayList<String> devname){
        ArrayList<String>optsn = new ArrayList<>();
        for (String item:devname) {
            String s = getOptSNbyName(item);
            if(s != null)
                optsn.add(s);
        }
        return optsn;
    }

    public String getOptSNbyName(String name){
        byte [] optsn = new byte[64];
        byte [] n = name.getBytes(StandardCharsets.UTF_8);//name.getBytes();
        boolean result;
        result = MkLib.Instance.mk_GetOptSnByName(n, optsn);
        int len = getByteCharterLen(optsn);
        return new String(optsn, 0, len+1);
    }

    private int getByteCharterLen(byte[] aa){
        int len = 0;
        for(int i=0; i<aa.length; i++){
            if(aa[i] != 0)
                len++;
        }
        return len;
    }

    // get device optical sn.
    public ArrayList<String> getDevOptSNList(){
        boolean result;
        byte [] name = new byte[64];
        byte [] optsn = new byte[64];
        ArrayList<String> list = new ArrayList<>();
        result = MkLib.Instance.mk_FindFirst(name);
        while(result){
            MkLib.Instance.mk_GetOptSnByName(name, optsn);
            int len = getByteCharterLen(optsn);
            String tmp = new String(optsn, 0, len+1);
            list.add(tmp);
            result = MkLib.Instance.mk_FindNext(name);
        }
        return list;
    }
}
