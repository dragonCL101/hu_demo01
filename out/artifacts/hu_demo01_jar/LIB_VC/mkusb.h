//#pragma once

#ifndef MKUSB_H
#define MKUSB_H

#include <windows.h>
#ifdef COMPILING_DLL
	#define DLLEXP __declspec(dllexport) 
#else
	#define DLLEXP __declspec(dllimport)
#endif

#ifdef __cplusplus
	extern "C" {
#endif

#define CALLTYPE __cdecl //__stdcall
//#define CALLTYPE __stdcall
DLLEXP unsigned int CALLTYPE mk_version();

DLLEXP bool CALLTYPE mk_Init(int isMonitor, int msec);

DLLEXP void CALLTYPE mk_Close();

DLLEXP bool CALLTYPE mk_IsUpdate();

DLLEXP bool CALLTYPE mk_SpDevScan();

DLLEXP bool CALLTYPE mk_FindFirst(char* name);

DLLEXP bool CALLTYPE mk_FindNext(char* name);

DLLEXP bool CALLTYPE mk_FindClose();

DLLEXP int CALLTYPE mk_GetDeviceCnt();

DLLEXP int CALLTYPE mk_GetOptSnByName(char *name, char* sn);

DLLEXP int  CALLTYPE mk_OpenSpDev_Assign(int order);

DLLEXP int  CALLTYPE mk_OpenSpDev(char* Name);

DLLEXP int CALLTYPE mk_OpenSpDev_OptSn(char *sn);

DLLEXP int  CALLTYPE mk_OpenSpDev_Angle(int i);

DLLEXP bool CALLTYPE mk_CloseSpDev(int i);

DLLEXP bool CALLTYPE mk_Msr_Capture(int i, unsigned short isAuto, unsigned int ExpTime);

DLLEXP bool CALLTYPE mk_Msr_CaptureAvg(int i, unsigned short isAuto, unsigned int ExpTime, unsigned int count);

DLLEXP bool CALLTYPE mk_Msr_Capture_multi(int *list, bool *result, int dev_cnt, unsigned short isAuto, unsigned int ExpTime);

DLLEXP bool CALLTYPE mk_Msr_Dark(int i);

DLLEXP bool CALLTYPE mk_Msr_GetDarkStus(int i, int *stus);

DLLEXP bool CALLTYPE mk_Msr_Dark_multi(int *list, int dev_cnt, bool *pErr);

DLLEXP bool CALLTYPE mk_Msr_SetMaxExpTime(int i, unsigned int maxtime);

DLLEXP bool CALLTYPE mk_Msr_SetExpMode(int i, unsigned int mode);

DLLEXP bool CALLTYPE mk_Msr_AutoDarkCtrl(int i, unsigned int ctl);

DLLEXP bool CALLTYPE mk_Msr_SetLightMode(int i, unsigned int mode, unsigned int freq);

DLLEXP bool CALLTYPE mk_Msr_SetLightModeFlt(int i, unsigned int mode, float freq);

DLLEXP bool CALLTYPE mk_Msr_SetLightMode_CycleNumber(int i, unsigned int cycnumber);

DLLEXP bool CALLTYPE mk_sr_SetLightMode_LCyc_Align(int i, unsigned int mode);

DLLEXP bool CALLTYPE mk_Msr_SetLightMode_LCyc_LeastSampleNumber(int i, unsigned int num);

DLLEXP bool CALLTYPE mk_Msr_SetLightMode_PreScanPara(int i, float time, float level);

DLLEXP bool CALLTYPE mk_Msr_SetDCOffset_Enable(int i, unsigned int ctrl);

DLLEXP bool CALLTYPE mk_Msr_SetLightMode_AutoExp(int i, unsigned int mode);

DLLEXP bool CALLTYPE mk_Msr_SetCIECalcMode(int i, unsigned int mode);

DLLEXP bool CALLTYPE mk_Msr_SetCorrMatrixCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_Msr_GetCorrMatrixPara(int i, unsigned int ch, char *name, double *data);

DLLEXP bool CALLTYPE mk_Msr_SetCorrMatrixPara(int i, unsigned int ch, char *name, double *data);

DLLEXP bool CALLTYPE mk_Msr_GenerateCorrMatrix(double *ref, double *mes, int len, double *corr, int colorspace, int colormethod);

DLLEXP bool CALLTYPE mk_Msr_SaveParaToDevice(int i);

DLLEXP bool CALLTYPE mk_Msr_SetCMCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_Msr_GetCMCH(int i, unsigned int *ch);

DLLEXP bool CALLTYPE mk_Msr_SetSpdWLSCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_Msr_GetSpdWLSCH(int i, unsigned int *ch);

DLLEXP bool CALLTYPE mk_Msr_SetSpdRatioCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_Msr_GetSpdRatioCH(int i, unsigned int *ch);

DLLEXP bool CALLTYPE mk_uc_ClearAllCH(int i);

DLLEXP bool CALLTYPE mk_uc_ClearCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_uc_SetCH(int i, unsigned int ch);

DLLEXP bool CALLTYPE mk_uc_GenerateCalPara(int i, wchar_t *root, wchar_t *ref_header, int ref_unit, wchar_t *dut_header, wchar_t *logfile_name, int ch, unsigned int *errmsg);

DLLEXP bool CALLTYPE mk_Msr_SetSpmRangeClip(int i, unsigned int ctrl);

DLLEXP bool CALLTYPE mk_Msr_SetLowLightFixedValue(int i, unsigned int ctrl, float value);

DLLEXP bool CALLTYPE mk_Msr_SetAutoDarkTemp(int i, unsigned int ctrl, float value, unsigned int tvalue);

DLLEXP bool CALLTYPE mk_GetData(int i, int type, float* data);

DLLEXP bool CALLTYPE mk_GetSpectrum(int i, int str, int stp, float* data);

DLLEXP bool CALLTYPE mk_SaveSpectrumToFile(int i, wchar_t *file);

DLLEXP bool CALLTYPE mk_SaveSpectrumToFileAscii(int i, char *file);

DLLEXP bool CALLTYPE mk_GetMicroMole(int i, int str, int stp, float *data);

DLLEXP bool CALLTYPE mk_GetOptSn(int i, char* sn);

DLLEXP int CALLTYPE mk_GetLightStrnegth(int i, unsigned int *peak);

DLLEXP bool CALLTYPE mk_ManualShutCtrl(int i, unsigned int ctl);

DLLEXP bool CALLTYPE mk_Peri_GetTemp(int i, float *data);

DLLEXP bool CALLTYPE mk_Peri_SetLCD(int i, unsigned short percent);

DLLEXP bool CALLTYPE mk_Peri_KeyEnable(int i);

DLLEXP bool CALLTYPE mk_Peri_KeyDisable(int i);

DLLEXP bool CALLTYPE mk_Peri_KeyClear(int i);

DLLEXP bool CALLTYPE mk_Peri_KeyGet(int i, unsigned int *key);

DLLEXP bool CALLTYPE mk_Info_Get(int i, int id, char *str_info);

DLLEXP bool CALLTYPE mk_flk_Dark(int i);

DLLEXP bool CALLTYPE mk_flk_SetPara(int i, unsigned int sample_num, unsigned int sample_freq, unsigned int fir_num, unsigned fir_cutfreq);

DLLEXP bool CALLTYPE mk_flk_Capture(int i, unsigned short isAutoGain, unsigned short gain, unsigned short enable_fir);

DLLEXP bool CALLTYPE mk_flk_GetData(int i, int type, float* data);

DLLEXP bool CALLTYPE mk_flk_GetTimeDomainWaveform(int i, int size, float* data);

DLLEXP bool CALLTYPE mk_flk_GetFreqDomainWaveform(int i, int size, float *freq, float* data);

DLLEXP bool CALLTYPE mk_Msr_GetErrMsg(int i, int *errcode, char *msg);

DLLEXP bool CALLTYPE mk_senr_Msr_Dark(int i);

DLLEXP bool CALLTYPE mk_senr_Msr_DarkEx(int i, int isLoadPara, int avgtimes);

DLLEXP bool CALLTYPE mk_senr_IsReDark(int i, float cond, unsigned int tcond);

DLLEXP bool CALLTYPE mk_senr_DarkCtrl_Auto(int i, unsigned int ctl);

DLLEXP bool CALLTYPE mk_senr_Msr_Capture(int i, unsigned short isAuto, unsigned int ExpTime);

DLLEXP bool CALLTYPE mk_senr_Msr_CaptureAvg(int i, unsigned short isAuto, unsigned int ExpTime, unsigned int num);

DLLEXP bool CALLTYPE mk_sflk_Dark(int i, unsigned int uexptime, unsigned int sampletime);

DLLEXP bool CALLTYPE mk_sflk_SetPara(int i, unsigned int sample_num, unsigned int sample_freq, unsigned int fir_num, unsigned fir_cutfreq);

DLLEXP bool CALLTYPE mk_sflk_Capture(int i, unsigned int uexptime, unsigned short enable_fir);

DLLEXP bool CALLTYPE mk_sflk_CaptureRaw(int i, unsigned int uexptime, void *ptr);

DLLEXP bool CALLTYPE mk_sflk_GetData(int i, int type, float* data);

DLLEXP bool CALLTYPE mk_senr_GetPeakSpRaw(int i, unsigned int exptime, unsigned int *data);

// manaul mode

DLLEXP int CALLTYPE mk_ManualScan();

DLLEXP int CALLTYPE mk_GetNumberOfDev();

DLLEXP bool CALLTYPE mk_Manual_DevMount(int id, char *sname);

DLLEXP bool CALLTYPE mk_Manual_DevUnMount(char *sname);

DLLEXP bool CALLTYPE mk_Manual_DevUnMountAll();

DLLEXP bool CALLTYPE mk_Manual_IsUPRDevice(int i, int timeout);

//DLLEXP bool CALLTYPE mk_Manual_DevNumber(int &num);


// SCPI
DLLEXP bool CALLTYPE mk_SCPI_Init(int comid, unsigned int isassign, HANDLE handle);

DLLEXP char* CALLTYPE mk_SCPI_sendCommand(char *cmd, int timeout);

DLLEXP bool CALLTYPE mk_SCPI_uc_GenerateCalPara(int devId, wchar_t *root, wchar_t *ref_header, int ref_unit, wchar_t *dut_header, wchar_t *logfile_name, int ch, unsigned int *errmsg);

#ifdef __cplusplus
	}
#endif

#endif