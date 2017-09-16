package com.readmain.task;

/**
 * Created by yuehao on 2017/9/15.
 */
public class ThreadTest01 {


    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
