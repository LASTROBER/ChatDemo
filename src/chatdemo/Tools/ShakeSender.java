package chatdemo.Tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ShakeSender extends Thread {
    private Thread t;
    private String threadName = "test";

    //将传入的面板存入自身
    private  JFrame jf;
    public ShakeSender(JFrame jframe) {
        jf = jframe;
    }

    //线程部分
    @Override
    public void run() {
        //获取屏幕像素尺寸
        Dimension screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        int wide = (int)width;
        double height = screenSize.getHeight();
        int high = (int)height;
        jf.setLocationRelativeTo(null);

        //循环抖动
        try {
            for(int i = 0; i < 10; i++) {
                jf.setLocation(wide / 2, high / 2);
                jf.setLocation(wide / 2 + 20, high / 2);
                jf.setLocation(wide / 2 - 20, high / 2);

                //抖动的间隔
                Thread.sleep(150);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    //启动部分
    @Override
    public void start () {
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
