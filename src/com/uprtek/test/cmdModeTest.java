package com.uprtek.test;
import com.huawei.cloudate.spectro.UprMeterMgr;
import com.huawei.cloudate.spectro.exception.SpectroExcetion;
import com.huawei.cloudate.spectro.UprMeter;

import static java.lang.Thread.*;

public class cmdModeTest {
    public static void main(String[] args) throws SpectroExcetion, InterruptedException {
        //test_init();
        fun_test();
    }

    static public void waitForExitProgram() {
        String c = null;
        java.io.BufferedReader buf = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        try{
            System.out.printf("Press Any key to exit program.\n");
            if((c = buf.readLine()) == null){
                return;
            }
        }catch(java.io.IOException ex){
            ex.printStackTrace();
        }
    }

    static public void fun_test() throws SpectroExcetion {
        //String sn = "17A00195";
        //String sn = "920OF101";
        String sn = "16L00998";
        UprMeter meter;

        UprMeterMgr mgr = new UprMeterMgr();
        System.out.printf("Require device connection sn : %s\n", sn);
        System.out.printf("Waiting for device connected...\n");
        while (true) {
            if (mgr.isUpdate()) {
                int cnt = mgr.getDevNum();
                if (cnt >= 1)
                    break;
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // using UprMeterMgr to create UprMeter or create by UprMeter itself.
        if(true) {
            meter = mgr.openDev(sn);
        }else{
            meter = new UprMeter();
        }
        if(meter==null){
            System.out.printf("Create UprMeter fail.\n");
            return;
        }

        System.out.printf("Device Init Start....\n");
        if(!meter.init(sn)){
            System.out.printf("Init fail\n");
            return;
        }

        for(int i=0; i<5; i++) {
            double[] spd = meter.measure();
            float lv, x, y, exp, tacktime;
            lv = meter.mValueLv;
            x = meter.mValuex;
            y = meter.mValuey;
            exp = meter.mValueExpTime;
            tacktime = meter.mValueTackTime;
            System.out.printf("%d, Lv, x, y : %5.4f, %5.4f, %5.4f, %.0fus, %5.3fs\n", i, lv, x, y, exp, tacktime);
            meter.export("spd.csv");
        }

        waitForExitProgram();
    }
    static public void test_init() throws InterruptedException {
        String sn = "17A00195";
        //String sn = "18J00272";
        UprMeter meter = new UprMeter();
        int i = 0;
        while(true){
            System.out.printf("Device Init Start....\n");
            sleep(1000);
            try {
                if(!meter.init(sn)){
                    System.out.printf("Init fail\n");
                    return;
                }else{
                    System.out.printf("Init success.");
                }
            } catch (SpectroExcetion spectroExcetion) {
                spectroExcetion.printStackTrace();
            }
            System.out.printf("Wait...\n");
            sleep(3000);
        }
    }
}
