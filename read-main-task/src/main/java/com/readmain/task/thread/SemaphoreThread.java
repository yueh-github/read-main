package com.readmain.task.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by yuehao on 2017/9/17.
 */
public class SemaphoreThread {


    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                        semaphore.release();
                        System.out.println(" --- --- -- ---- ----- --- -- - - :: : :: :" + Thread.currentThread().getName());
                    } catch (Exception ex) {
                    } finally {
                    }
                }
            }).start();
        }
    }
}
