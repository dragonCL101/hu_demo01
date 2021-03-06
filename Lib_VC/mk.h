#ifndef MK_H
#define MK_H
#define DATA_CLR_BASE	0x0000
#define DATA_CRI_BASE	0x1000
#define DATA_GAI_BASE	0x2000
#define DATA_CQS_BASE	0x3000
#define DATA_TLCI_BASE	0x4000
#define DATA_TM30_BASE	0x5000
#define DATA_OTH_BASE   0x8000

#define DATA_CIE1931_x  	(DATA_CLR_BASE | 0x0001)
#define DATA_CIE1931_y		(DATA_CLR_BASE | 0x0002)
#define DATA_CIE_X			(DATA_CLR_BASE | 0x0003)
#define DATA_CIE_Y			(DATA_CLR_BASE | 0x0004)
#define DATA_CIE_Z			(DATA_CLR_BASE | 0x0005)
#define DATA_CIE1931_X		(DATA_CLR_BASE | 0x0003)
#define DATA_CIE1931_Y		(DATA_CLR_BASE | 0x0004)
#define DATA_CIE1931_Z		(DATA_CLR_BASE | 0x0005)
#define DATA_CIE1964_x10	(DATA_CLR_BASE | 0x0006)
#define DATA_CIE1964_y10	(DATA_CLR_BASE | 0x0007)
#define DATA_CIE1964_X10	(DATA_CLR_BASE | 0x0008)
#define DATA_CIE1964_Y10	(DATA_CLR_BASE | 0x0009)
#define DATA_CIE1964_Z10	(DATA_CLR_BASE | 0x000A)
#define DATA_CIE1976_up 	(DATA_CLR_BASE | 0x0011)
#define DATA_CIE1976_vp 	(DATA_CLR_BASE | 0x0012)
#define DATA_CIE1976_up10 	(DATA_CLR_BASE | 0x0013)
#define DATA_CIE1976_vp10 	(DATA_CLR_BASE | 0x0014)
#define DATA_CIE1960_du 	(DATA_CLR_BASE | 0x0021)
#define DATA_CIE1960_dv 	(DATA_CLR_BASE | 0x0022)
#define DATA_CIE_duv		(DATA_CLR_BASE | 0x0023)
#define DATA_CIE1960_u 		(DATA_CLR_BASE | 0x0024)
#define DATA_CIE1960_v 		(DATA_CLR_BASE | 0x0025)
#define DATA_LUX			(DATA_CLR_BASE | 0x0101)
#define DATA_LV				(DATA_CLR_BASE | 0x0101)
#define DATA_FootCandle		(DATA_CLR_BASE | 0x0102)
#define DATA_CCT			(DATA_CLR_BASE | 0x0103)
#define DATA_LUX_S			(DATA_CLR_BASE | 0x0104)
#define DATA_LUX_SP			(DATA_CLR_BASE | 0x0105)
#define DATA_LUX_P			(DATA_CLR_BASE | 0x0106)
#define DATA_Purity			(DATA_CLR_BASE | 0x0201)
#define DATA_LambdaP		(DATA_CLR_BASE | 0x0202)
#define DATA_LambdaD		(DATA_CLR_BASE | 0x0203)
#define DATA_LambdaPV		(DATA_CLR_BASE | 0x0204)
#define DATA_PPF			(DATA_CLR_BASE | 0x0301)
#define DATA_PPFR			(DATA_CLR_BASE | 0x0302)
#define DATA_PPFG			(DATA_CLR_BASE | 0x0303)
#define DATA_PPFB			(DATA_CLR_BASE | 0x0304)
#define DATA_ExpTime		(DATA_CLR_BASE | 0x0401)
#define DATA_TackTime		(DATA_CLR_BASE | 0x0402)

#define DATA_CRI_RA		(DATA_CRI_BASE | 0x0001)
#define DATA_CRI_R1		(DATA_CRI_BASE | 0x0002)
#define DATA_CRI_R2		(DATA_CRI_BASE | 0x0003)
#define DATA_CRI_R3		(DATA_CRI_BASE | 0x0004)
#define DATA_CRI_R4		(DATA_CRI_BASE | 0x0005)
#define DATA_CRI_R5		(DATA_CRI_BASE | 0x0006)
#define DATA_CRI_R6		(DATA_CRI_BASE | 0x0007)
#define DATA_CRI_R7		(DATA_CRI_BASE | 0x0008)
#define DATA_CRI_R8		(DATA_CRI_BASE | 0x0009)
#define DATA_CRI_R9		(DATA_CRI_BASE | 0x000A)
#define DATA_CRI_R10	(DATA_CRI_BASE | 0x000B)
#define DATA_CRI_R11	(DATA_CRI_BASE | 0x000C)
#define DATA_CRI_R12	(DATA_CRI_BASE | 0x000D)
#define DATA_CRI_R13	(DATA_CRI_BASE | 0x000E)
#define DATA_CRI_R14	(DATA_CRI_BASE | 0x000F)
#define DATA_CRI_R15	(DATA_CRI_BASE | 0x0010)

#define DATA_GAI			(DATA_GAI_BASE | 0x0000)
#define DATA_GAI_up			(DATA_GAI_BASE | 0x0001)
#define DATA_GAI_vp			(DATA_GAI_BASE | 0x0002)
#define DATA_GAI_TCS1_up		(DATA_GAI_BASE | 0x0003)
#define DATA_GAI_TCS1_vp		(DATA_GAI_BASE | 0x0004)
#define DATA_GAI_TCS2_up		(DATA_GAI_BASE | 0x0005)
#define DATA_GAI_TCS2_vp		(DATA_GAI_BASE | 0x0006)
#define DATA_GAI_TCS3_up		(DATA_GAI_BASE | 0x0007)
#define DATA_GAI_TCS3_vp		(DATA_GAI_BASE | 0x0008)
#define DATA_GAI_TCS4_up		(DATA_GAI_BASE | 0x0009)
#define DATA_GAI_TCS4_vp		(DATA_GAI_BASE | 0x000A)
#define DATA_GAI_TCS5_up		(DATA_GAI_BASE | 0x000B)
#define DATA_GAI_TCS5_vp		(DATA_GAI_BASE | 0x000C)
#define DATA_GAI_TCS6_up		(DATA_GAI_BASE | 0x000D)
#define DATA_GAI_TCS6_vp		(DATA_GAI_BASE | 0x000E)
#define DATA_GAI_TCS7_up		(DATA_GAI_BASE | 0x000F)
#define DATA_GAI_TCS7_vp		(DATA_GAI_BASE | 0x0010)
#define DATA_GAI_TCS8_up		(DATA_GAI_BASE | 0x0011)
#define DATA_GAI_TCS8_vp		(DATA_GAI_BASE | 0x0012)

#define DATA_CQS			(DATA_CQS_BASE | 0x0000)
#define DATA_CQS_Q1			(DATA_CQS_BASE | 0x0001)
#define DATA_CQS_Q2			(DATA_CQS_BASE | 0x0002)
#define DATA_CQS_Q3			(DATA_CQS_BASE | 0x0003)
#define DATA_CQS_Q4			(DATA_CQS_BASE | 0x0004)
#define DATA_CQS_Q5			(DATA_CQS_BASE | 0x0005)
#define DATA_CQS_Q6			(DATA_CQS_BASE | 0x0006)
#define DATA_CQS_Q7			(DATA_CQS_BASE | 0x0007)
#define DATA_CQS_Q8			(DATA_CQS_BASE | 0x0008)
#define DATA_CQS_Q9			(DATA_CQS_BASE | 0x0009)
#define DATA_CQS_Q10		(DATA_CQS_BASE | 0x000A)
#define DATA_CQS_Q11		(DATA_CQS_BASE | 0x000B)
#define DATA_CQS_Q12		(DATA_CQS_BASE | 0x000C)
#define DATA_CQS_Q13		(DATA_CQS_BASE | 0x000D)
#define DATA_CQS_Q14		(DATA_CQS_BASE | 0x000E)
#define DATA_CQS_Q15		(DATA_CQS_BASE | 0x000F)

#define DATA_TLCI			(DATA_TLCI_BASE | 0x0000)

#define DATA_TM30_RF		(DATA_TM30_BASE | 0x0000)
#define DATA_TM30_RG		(DATA_TM30_BASE | 0x0001)

#define INFO_MODELNAME	0x1001
#define INFO_HWVER 		0x1002
#define INFO_FWVER		0x1003
#define INFO_WLSTR		0x1004
#define INFO_WLEND		0x1005
#define INFO_MANF		0x1006
#define INFO_MA_SN		0x1101
#define INFO_MA_ID		0x1102

// Flicker value
#define FLK_RMS_VALUE	0x0001
#define FLK_RMS_DB		0x0002
#define FLK_JEITA_VALUE 0x0003
#define FLK_JEITA_DB	0x0004
#define FLK_VESA_VALUE	0x0005
#define FLK_VESA_DB		0x0006
#define FLK_MAX_VALUE	0x0007
#define FLK_MIN_VALUE	0x0008
#define FLK_AVG_VALUE	0x0009
#define FLK_FMA_VALUE	0x000A
#define FLK_IES_INDEX_VALUE	0x000B
#define FLK_IES_PERCENT_VALUE 0x000C
#define FLK_SVM_VALUE 0x000D
#define FLK_FFT_FREQ 0x000E
#define FLK_FDA_FREQ_FPS 0x0101
#define FLK_FDA_FREQ_MAIN 0x0102
#define FLK_FDA_FREQ_SUB 0x0103

#define DEV_ERR_SUCCESS 0
#define DEV_ERR_MOUNTED -1
#define DEV_ERR_DARK -2
#define DEV_ERR_DARK_LOADPARA -3
#define DEV_ERR_MOUNT_SHUTTER_COM -4
#define DEV_ERR_SAVE_INI -5

#endif