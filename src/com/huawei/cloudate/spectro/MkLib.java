package com.huawei.cloudate.spectro;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ByReference;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;

public interface MkLib extends Library{
    String lib = ".\\Lib_VC\\x64\\mkusb.dll";
    // String lib = "mkusb.dll";
    MkLib Instance = (MkLib) Native.loadLibrary(lib, MkLib.class);

    public long mk_version();

    public void mk_Init(int isMonitor, int msec);

    public void mk_Close();

    public boolean mk_IsUpdate();

    public boolean mk_SpDevScan();

    public boolean mk_FindFirst(byte[] name);

    public boolean mk_FindNext(byte[] name);

    public boolean mk_FindClose();

    public int mk_GetDeviceCnt();

    public int mk_GetDeviceUsedCnt();

    public int mk_GetDeviceUnusedCnt();

    public boolean mk_GetOptSnByName(byte[] name, byte[] sn);

    public int mk_OpenSpDev_Assign(int order);

    public int mk_OpenSpDev(byte[] Name);

    public int mk_OpenSpDev_OptSn(byte[] sn);

    public int mk_OpenSpDev_Angle(int i);

    public boolean mk_CloseSpDev(int i);

    public boolean mk_Msr_Capture(int i, short isAuto, int ExpTime);

    public boolean mk_Msr_CaptureAvg(int i, short isAuto, int ExpTime, int count);

    public boolean mk_Msr_Capture_multi(
            IntByReference list, ByReference result, int dev_cnt, short isAuto, int ExpTime);

    public boolean mk_Msr_Dark(int i);

    public boolean mk_Msr_GetDarkStus(int i, IntByReference stus);

    public boolean mk_Msr_Dark_multi(IntByReference list, int dev_cnt, ByReference pErr);

    public boolean mk_Msr_SetMaxExpTime(int i, int maxtime);

    public boolean mk_Msr_SetExpMode(int i, int mode);

    public boolean mk_Msr_AutoDarkCtrl(int i, int ctl);

    public boolean mk_Msr_SetLightMode(int i, int mode, int freq);

    public boolean mk_Msr_SetLightModeFlt(int i, int mode, float freq);

    public boolean mk_Msr_SetLightMode_CycleNumber(int i, int cycnumber);

    public boolean mk_Msr_SetLightMode_LCyc_Align(int i, int mode);

    public boolean mk_Msr_SetLightMode_LCyc_LeastSampleNumber(int i, int num);

    public boolean mk_Msr_SetLightMode_PreScanPara(int i, float time, float level);

    public boolean mk_Msr_SetDCOffset_Enable(int i, int ctrl);

    public boolean mk_Msr_SetLightMode_AutoExp(int i, int mode);

    public boolean mk_Msr_SetCIECalcMode(int i, int mode);

    public boolean mk_Msr_SetCorrMatrixCH(int i, int ch);

    public boolean mk_Msr_GetCorrMatrixPara(int i, int ch, ByReference name, DoubleByReference data);

    public boolean mk_Msr_SetCorrMatrixPara(int i, int ch, ByReference name, DoubleByReference data);

    public boolean mk_Msr_GenerateCorrMatrix(DoubleByReference ref, DoubleByReference mes, int len,
                                             DoubleByReference corr, int colorspace, int colormethod);

    public boolean mk_Msr_SetSpdWLSCH(int i, int ch);

    public boolean mk_Msr_SetSpdRatioCH(int i, int ch);

    public boolean mk_Msr_SetCMCH(int i, int ch);

    public boolean mk_uc_ClearAllCH(int i);

    public boolean mk_uc_ClearCH(int i, int ch);

    public boolean mk_uc_SetCH(int i, int ch);

    public boolean mk_uc_GenerateCalPara(int i, ByReference root, ByReference ref_header, ByReference dut_header, ByReference logfile_name, int ch, IntByReference errmsg);

    public boolean mk_Msr_SetSpmRangeClip(int i, int ctrl);

    public boolean mk_Msr_SetLowLightFixedValue(int i, int ctrl, float value);

    public boolean mk_Msr_SetBoxcarFilter(int i, int ctrl, int enHR, int size);

    public boolean mk_Msr_SetAutoDarkTemp(int i, int ctrl, float value, int tvalue);

    public boolean mk_GetData(int i, int type, FloatByReference data);

    public boolean mk_GetSpectrum(int i, int str, int stp, Pointer ptr);

    public boolean mk_SaveSpectrumToFile(int i, ByReference file);

    public boolean mk_SaveSpectrumToFileAscii(int i, ByReference file);

    public boolean mk_GetMicroMole(int i, int str, int stp, FloatByReference data);

    public boolean mk_GetOptSn(int i, byte[] sn);

    public int mk_GetLightStrnegth(int i);

    public boolean mk_ManualShutCtrl(int i, int ctl);

    public boolean mk_Peri_GetTemp(int i, FloatByReference data);

    public boolean mk_Peri_SetLCD(int i, short percent);

    public boolean mk_Peri_KeyEnable(int i);

    public boolean mk_Peri_KeyDisable(int i);

    public boolean mk_Peri_KeyClear(int i);

    public boolean mk_Peri_KeyGet(int i, IntByReference key);

    public boolean mk_Info_Get(int i, int id, byte [] str);

    public boolean mk_flk_Dark(int i);

    public boolean mk_flk_SetPara(int i, int sample_num, int sample_freq, int fir_num, float fir_cutfreq);

    public boolean mk_flk_Capture(int i, short isAutoGain, short gain, short enable_fir);

    public boolean mk_flk_GetData(int i, int type, FloatByReference data);

    public boolean mk_flk_GetTimeDomainWaveform(int i, int size, FloatByReference data);

    public boolean mk_flk_GetFreqDomainWaveform(int i, int size, FloatByReference freq, FloatByReference data);

    public boolean mk_senr_Msr_Dark(int i);

    public boolean mk_senr_Msr_DarkEx(int i, int isLoadPara, int avgtimes);

    public boolean mk_senr_IsReDark(int i, float cond, int tcond);

    public boolean mk_senr_DarkCtrl_Auto(int i, int ctl);

    public boolean mk_senr_Msr_Capture(int i, int isAuto, int ExpTime);

    public boolean mk_senr_Msr_CaptureAvg(int i, int isAuto, int ExpTime, int num);

    public boolean mk_sflk_Dark(int i, int uexptime, int sampletime);

    public boolean mk_sflk_SetPara(int i, int sample_num, int sample_freq, int fir_num, int fir_cutfreq);

    public boolean mk_sflk_Capture(int i, int uexptime, int enable_fir);

    public boolean mk_sflk_CaptureRaw(int i, int uexptime, ByReference ptr);

    public boolean mk_sflk_GetData(int i, int type, FloatByReference data);

    public boolean mk_senr_GetPeakSpRaw(int i, int exptime, IntByReference data);

    public int mk_ManualScan();

    public int mk_GetNumberOfDev();

    public boolean mk_Manual_DevMount(int id, ByReference sname);

    public boolean mk_Manual_DevUnMount(ByReference sname);

    public boolean mk_Manual_DevUnMountAll();

    public boolean mk_Manual_IsUPRDevice(int i, int timeout);

    public boolean mk_Msr_GetErrMsg(int i, IntByReference errcode, byte [] err);


    final int DATA_CLR_BASE = 0x0000;
    final int DATA_CRI_BASE = 0x1000;
    final int DATA_GAI_BASE = 0x2000;
    final int DATA_CQS_BASE = 0x3000;
    final int DATA_TLCI_BASE = 0x4000;
    final int DATA_TM30_BASE = 0x5000;
    final int DATA_OTH_BASE = 0x8000;

    final int DATA_CIE1931_x = (DATA_CLR_BASE | 0x0001);
    final int DATA_CIE1931_y = (DATA_CLR_BASE | 0x0002);
    final int DATA_CIE_X = (DATA_CLR_BASE | 0x0003);
    final int DATA_CIE_Y = (DATA_CLR_BASE | 0x0004);
    final int DATA_CIE_Z = (DATA_CLR_BASE | 0x0005);
    final int DATA_CIE1931_X = (DATA_CLR_BASE | 0x0003);
    final int DATA_CIE1931_Y = (DATA_CLR_BASE | 0x0004);
    final int DATA_CIE1931_Z = (DATA_CLR_BASE | 0x0005);

    final int DATA_CIE1964_x10 =(DATA_CLR_BASE |0x0006);

    final int DATA_CIE1964_y10 = (DATA_CLR_BASE |0x0007);

    final int DATA_CIE1964_X10 = (DATA_CLR_BASE |0x0008);

    final int DATA_CIE1964_Y10 = (DATA_CLR_BASE |0x0009);

    final int DATA_CIE1964_Z10 = (DATA_CLR_BASE |0x000A);

    final int DATA_CIE1976_up = (DATA_CLR_BASE | 0x0011);
    final int DATA_CIE1976_vp = (DATA_CLR_BASE | 0x0012);
    final int DATA_CIE1976_up10 = (DATA_CLR_BASE | 0x0013);
    final int DATA_CIE1976_vp10 = (DATA_CLR_BASE | 0x0014);
    final int DATA_CIE1960_du = (DATA_CLR_BASE | 0x0021);
    final int DATA_CIE1960_dv = (DATA_CLR_BASE | 0x0022);
    final int DATA_CIE_duv = (DATA_CLR_BASE | 0x0023);
    final int DATA_CIE1960_u = (DATA_CLR_BASE | 0x0024);
    final int DATA_CIE1960_v = (DATA_CLR_BASE | 0x0025);
    final int DATA_LUX = (DATA_CLR_BASE | 0x0101);
    final int DATA_LV = (DATA_CLR_BASE | 0x0101);
    final int DATA_FootCandle = (DATA_CLR_BASE | 0x0102);
    final int DATA_CCT = (DATA_CLR_BASE | 0x0103);
    final int DATA_LUX_S = (DATA_CLR_BASE | 0x0104);
    final int DATA_LUX_SP = (DATA_CLR_BASE | 0x0105);
    final int DATA_LUX_P = (DATA_CLR_BASE | 0x0106);
    final int DATA_Purity = (DATA_CLR_BASE | 0x0201);
    final int DATA_LambdaP = (DATA_CLR_BASE | 0x0202);
    final int DATA_LambdaD = (DATA_CLR_BASE | 0x0203);
    final int DATA_LambdaPV = (DATA_CLR_BASE | 0x0204);
    final int DATA_PPF = (DATA_CLR_BASE | 0x0301);
    final int DATA_PPFR = (DATA_CLR_BASE | 0x0302);
    final int DATA_PPFG = (DATA_CLR_BASE | 0x0303);
    final int DATA_PPFB = (DATA_CLR_BASE | 0x0304);
    final int DATA_ExpTime = (DATA_CLR_BASE | 0x0401);
    final int DATA_TackTime = (DATA_CLR_BASE | 0x0402);

    final int DATA_CRI_RA = (DATA_CRI_BASE | 0x0001);
    final int DATA_CRI_R1 = (DATA_CRI_BASE | 0x0002);
    final int DATA_CRI_R2 = (DATA_CRI_BASE | 0x0003);
    final int DATA_CRI_R3 = (DATA_CRI_BASE | 0x0004);
    final int DATA_CRI_R4 = (DATA_CRI_BASE | 0x0005);
    final int DATA_CRI_R5 = (DATA_CRI_BASE | 0x0006);
    final int DATA_CRI_R6 = (DATA_CRI_BASE | 0x0007);
    final int DATA_CRI_R7 = (DATA_CRI_BASE | 0x0008);
    final int DATA_CRI_R8 = (DATA_CRI_BASE | 0x0009);
    final int DATA_CRI_R9 = (DATA_CRI_BASE | 0x000A);

    final int DATA_CRI_R10 = (DATA_CRI_BASE |0x000B);

    final int DATA_CRI_R11 = (DATA_CRI_BASE |0x000C);

    final int DATA_CRI_R12 = (DATA_CRI_BASE |0x000D);

    final int DATA_CRI_R13 = (DATA_CRI_BASE |0x000E);

    final int DATA_CRI_R14 = (DATA_CRI_BASE |0x000F);

    final int DATA_CRI_R15 = (DATA_CRI_BASE |0x0010);

    final int DATA_GAI = (DATA_GAI_BASE | 0x0000);
    final int DATA_GAI_up = (DATA_GAI_BASE | 0x0001);
    final int DATA_GAI_vp = (DATA_GAI_BASE | 0x0002);
    final int DATA_GAI_TCS1_up = (DATA_GAI_BASE | 0x0003);
    final int DATA_GAI_TCS1_vp = (DATA_GAI_BASE | 0x0004);
    final int DATA_GAI_TCS2_up = (DATA_GAI_BASE | 0x0005);
    final int DATA_GAI_TCS2_vp = (DATA_GAI_BASE | 0x0006);
    final int DATA_GAI_TCS3_up = (DATA_GAI_BASE | 0x0007);
    final int DATA_GAI_TCS3_vp = (DATA_GAI_BASE | 0x0008);
    final int DATA_GAI_TCS4_up = (DATA_GAI_BASE | 0x0009);
    final int DATA_GAI_TCS4_vp = (DATA_GAI_BASE | 0x000A);
    final int DATA_GAI_TCS5_up = (DATA_GAI_BASE | 0x000B);
    final int DATA_GAI_TCS5_vp = (DATA_GAI_BASE | 0x000C);
    final int DATA_GAI_TCS6_up = (DATA_GAI_BASE | 0x000D);
    final int DATA_GAI_TCS6_vp = (DATA_GAI_BASE | 0x000E);
    final int DATA_GAI_TCS7_up = (DATA_GAI_BASE | 0x000F);
    final int DATA_GAI_TCS7_vp = (DATA_GAI_BASE | 0x0010);
    final int DATA_GAI_TCS8_up = (DATA_GAI_BASE | 0x0011);
    final int DATA_GAI_TCS8_vp = (DATA_GAI_BASE | 0x0012);

    final int DATA_CQS = (DATA_CQS_BASE | 0x0000);
    final int DATA_CQS_Q1 = (DATA_CQS_BASE | 0x0001);
    final int DATA_CQS_Q2 = (DATA_CQS_BASE | 0x0002);
    final int DATA_CQS_Q3 = (DATA_CQS_BASE | 0x0003);
    final int DATA_CQS_Q4 = (DATA_CQS_BASE | 0x0004);
    final int DATA_CQS_Q5 = (DATA_CQS_BASE | 0x0005);
    final int DATA_CQS_Q6 = (DATA_CQS_BASE | 0x0006);
    final int DATA_CQS_Q7 = (DATA_CQS_BASE | 0x0007);
    final int DATA_CQS_Q8 = (DATA_CQS_BASE | 0x0008);
    final int DATA_CQS_Q9 = (DATA_CQS_BASE | 0x0009);
    final int DATA_CQS_Q10 = (DATA_CQS_BASE | 0x000A);
    final int DATA_CQS_Q11 = (DATA_CQS_BASE | 0x000B);
    final int DATA_CQS_Q12 = (DATA_CQS_BASE | 0x000C);
    final int DATA_CQS_Q13 = (DATA_CQS_BASE | 0x000D);
    final int DATA_CQS_Q14 = (DATA_CQS_BASE | 0x000E);
    final int DATA_CQS_Q15 = (DATA_CQS_BASE | 0x000F);

    final int DATA_TLCI = (DATA_TLCI_BASE | 0x0000);

    final int DATA_TM30_RF = (DATA_TM30_BASE | 0x0000);
    final int DATA_TM30_RG = (DATA_TM30_BASE | 0x0001);

    final int INFO_MODELNAME = 0x1001;
    final int INFO_HWVER = 0x1002;
    final int INFO_FWVER = 0x1003;
    final int INFO_WLSTR = 0x1004;
    final int INFO_WLEND = 0x1005;

    // Flicker value
    final int FLK_RMS_VALUE = 0x0001;
    final int FLK_RMS_DB = 0x0002;
    final int FLK_JEITA_VALUE = 0x0003;
    final int FLK_JEITA_DB = 0x0004;
    final int FLK_VESA_VALUE = 0x0005;
    final int FLK_VESA_DB = 0x0006;
    final int FLK_MAX_VALUE = 0x0007;
    final int FLK_MIN_VALUE = 0x0008;
    final int FLK_AVG_VALUE = 0x0009;
    final int FLK_FMA_VALUE = 0x000A;
    final int FLK_IES_INDEX_VALUE = 0x000B;
    final int FLK_IES_PERCENT_VALUE = 0x000C;
    final int FLK_SVM_VALUE = 0x000D;
    final int FLK_FREQ = 0x000E;
}
