package com.uprtek.test;

import com.huawei.cloudate.spectro.UprMeter;
import com.huawei.cloudate.spectro.UprMeterMgr;
import com.huawei.cloudate.spectro.exception.SpectroExcetion;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

class JobMsr implements Runnable{

    public final static int JOB_CMD_INIT = 0;
    public final static int JOB_CMD_MSR = 1;

    public final static int JOB_STUS_IDLE = 0;
    public final static int JOB_STUS_RUNNING = 1;
    public final static int JOB_STUS_EXIT = 99;

    // parameter
    private String mDevName;
    private ConcurrentLinkedQueue<String> mMsgQueue;    // offser, insert, pool, remove
    private UprMeter mMeter;
    private ArrayBlockingQueue<Integer> mCmdQueue;
    private int mMsrTimes;
    volatile public int mStatus;    // 0, for busy, 1 for idle;
    private AtomicBoolean mIsExit;
    public JobFinish mJobFinish;
    volatile private int mIsMsrStop;

    public JobMsr(UprMeter meter){
        mMsgQueue = new ConcurrentLinkedQueue<>();
        mMeter = meter;
        mCmdQueue = new ArrayBlockingQueue<Integer>(10);
        mMsrTimes = 20000;
        mStatus = JOB_STUS_IDLE;
        mIsExit = new AtomicBoolean(false);
    }

    public void setFinishCB(JobFinish cb){
        mJobFinish = cb;
    }
    public String getMsg(){
        if(mMsgQueue.isEmpty())
            return null;
        return mMsgQueue.poll();
    }

    public boolean putMsg(String sn){
        if(mMsgQueue.size()==100)
            return false;
        mMsgQueue.offer(sn);
        System.out.printf(sn);
        return true;
    }

    public boolean cmd_Init(){
        if(mStatus != JOB_STUS_IDLE)
            return false;
        mStatus = JOB_STUS_RUNNING;
        mIsMsrStop = 0;
        try {
            mCmdQueue.put(JOB_CMD_INIT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean cmd_Msr(int times){
        if(mStatus != JOB_STUS_IDLE)
            return false;
        mStatus = JOB_STUS_RUNNING;
        mIsMsrStop = 0;
        mMsrTimes = times;
        try {
            mCmdQueue.put(JOB_CMD_MSR);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean cmd_MsrStop(){
        mIsMsrStop = 1;
        return true;
    }
    public boolean askExit(){
        mIsExit.set(true);
        return true;
    }

    @Override
    public void run() {
        // print message
        while(mIsExit.get() == false){
            int cmd = 0;//mCmdQueue.poll();
            try {
                cmd = mCmdQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (cmd) {
                case JOB_CMD_INIT:
                        putMsg("Msr Init Start.\n");
                        try {
                            mMeter.init(null);
                        } catch (SpectroExcetion spectroExcetion) {
                            spectroExcetion.printStackTrace();
                        }
                        putMsg("Msr Init Finish.\n");
                        mStatus = 1;
                    break;
                case JOB_CMD_MSR:
                    System.out.printf("Msr command Start.\n");
                    for (int i = 0; i < mMsrTimes; i++) {
                        if(mIsMsrStop == 0){
                            try {
                                mMeter.measure();
                                float lv, x, y, exp, tacktime;
                                lv = mMeter.mValueLv;
                                x = mMeter.mValuex;
                                y = mMeter.mValuey;
                                exp = mMeter.mValueExpTime;
                                tacktime = mMeter.mValueTackTime;
                                String msg = String.format("%d\tLv,x,y:\t%5.4f\t%5.4f\t%5.4f\t%.0fus\t%5.3fs\n", i+1, lv, x, y, exp, tacktime);
                                mMsgQueue.offer(msg);
                                //System.out.printf("%s\n", msg);
                            } catch (SpectroExcetion spectroExcetion) {
                                spectroExcetion.printStackTrace();
                            }
                        }
                    }
                    if(mMsrTimes > 1)
                        mJobFinish.finish();
                    break;
            }
            mStatus = JOB_STUS_IDLE;
        }
        mStatus = JOB_STUS_EXIT;
    }

    interface JobFinish{
        void finish();
    }
}