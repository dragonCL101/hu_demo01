package com.uprtek.test;

import com.huawei.cloudate.spectro.UprMeter;
import com.huawei.cloudate.spectro.UprMeterMgr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MultiTestUI implements ActionListener{
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton btnConnection;
    private JTextArea taDeviceInfo;
    private JPanel plMain;
    private JCheckBox chkAutoFillSN;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JButton btnInit;
    private JCheckBox chkAutoExpTIme;
    private JTextField edtExpTIme;
    private JTextField edtMaxExpTime;
    private JCheckBox chkConMsr;
    private JTextField edtMsrNum;
    private JButton btnMsr;
    private JButton btnStop;
    private UprMeter mMeter;
    private UprMeterMgr mMeterMgr;
    private Timer mTimer;
    private ArrayList<JobMsr> mJobList;
    private int mIsCreated;
    JobMsr.JobFinish mFinishCB;

    public MultiTestUI() {
        mMeterMgr = new UprMeterMgr();
        mTimer = new Timer(300, this);
        mIsCreated = 0;

        mFinishCB = new JobMsr.JobFinish() {
            @Override
            public void finish() {
                java.awt.Toolkit.getDefaultToolkit().beep();
                java.awt.Toolkit.getDefaultToolkit().beep();
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
        };
        btnConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                showMsg("connection device ...\n");
                if(mIsCreated == 0){
                    int cnt = mMeterMgr.getDevNum();
                    int num = (cnt < 16)?16:cnt;
                    if(mJobList==null) {
                        mJobList = new ArrayList<>(num);
                    }else{
                        if(mJobList.size() < num)
                            mJobList = new ArrayList<>(num);
                    }

                    for (String itn:mMeterMgr.getDevOptSNList()
                         ) {
                        UprMeter meter = mMeterMgr.openDev(itn.trim());
                        JobMsr job = new JobMsr(meter);
                        job.setFinishCB(mFinishCB);
                        mJobList.add(job);
                    }

                    for (JobMsr itn:mJobList
                         ) {
                        new Thread(itn).start();
                    }
                    taDeviceInfo.append(String.format("Create task : %d\n", mJobList.size()));
                }else{

                }
                mIsCreated ^= 1;
            }
        });

        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mJobList != null && mIsCreated==1){
                    for (JobMsr itn:mJobList
                         ) {
                        if(!itn.cmd_Init()){
                            showMsg("Init Cmd. Device is Busy.\n");
                        }else
                            showMsg("Init Cmd.\n");
                    }
                }
            }
        });

        btnMsr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mJobList != null && mIsCreated==1){
                    for (JobMsr itn:mJobList
                    ) {
                        int num = Integer.valueOf(edtMsrNum.getText().trim());
                        if(itn.cmd_Msr(num) == false){
                            showMsg("MSR Cmd. Device is Busy.\n");
                        }else
                            showMsg("MSR Cmd.\n");
                    }
                }
            }
        });
        mTimer.start();
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mJobList != null && mIsCreated==1){
                    for (JobMsr itn:mJobList
                    ) {
                        itn.cmd_MsrStop();
                        showMsg("Ask MSR STOP.\n");
                    }
                }

            }
        });
    }

    String getCurrTime(){
        //SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public void showMsg(String str) {
        String t = getCurrTime();
        taDeviceInfo.append(t + "\t" + str);
    }
    public static void main(String[] args) {
        MultiTestUI test = new MultiTestUI();

        JFrame frame = new JFrame("MultiTest");
        frame.setContentPane(test.plMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 600, 400);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2 - frame.getWidth()/2, dim.height/2-frame.getHeight()/2);
        frame.setVisible(true);
        //MultiTestUI test = new MultiTestUI();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(mMeterMgr.isUpdate()) {
            //ArrayList<String> list = null;
            ArrayList<String> snlist = null;
            //list = mMeter.getDevSNList();
            //snlist = mMeter.getOptSNList(list);
            snlist = mMeterMgr.getDevOptSNList();
            taDeviceInfo.selectAll();
            taDeviceInfo.replaceSelection("");
            for (String item : snlist) {
                taDeviceInfo.append(item.trim() + "\n");
            }
        }

        //
        if(mJobList != null){
            int ch = 0;
            String str;
            for (JobMsr itn:mJobList
                 ) {
                while(true){
                    str = itn.getMsg();
                    if(str != null) {
                        switch (ch) {
                            case 0:
                                textArea1.append(str);
                                break;
                            case 1:
                                textArea2.append(str);
                                break;
                            case 2:
                                textArea3.append(str);
                                break;
                            case 3:
                                textArea4.append(str);
                                break;
                        }
                    }else {
                        ch++;
                        break;
                    }
                }
            }
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
