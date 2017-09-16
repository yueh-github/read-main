package com.readmain.task.thread;

import java.util.concurrent.*;

/**
 * Created by yuehao on 2017/9/17.
 */
public class TestThreadPool {


    private static CountDownLatch countDownLatch = new CountDownLatch(100000);


    //单个线程
    private static ExecutorService executorServiceSingle = Executors.newSingleThreadExecutor();

    //有限容量线程池
    private static ExecutorService executorServiceFixed = Executors.newFixedThreadPool(100);

    //无线扩容线城池
    private static ExecutorService executorServiceCache =  Executors.newCachedThreadPool();

    //自定义线程池
//    private static ThreadPoolExecutor myExecutorService = new ThreadPoolExecutor(200,150,60, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
    // 测试线程池
    public static void main(String[] args) {

        try {
            long startDate = System.currentTimeMillis();
            for (int i = 0; i < 100000; i++) {
                executorServiceCache.execute(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(10);
                        } catch (Exception ex) {

                        }
                        countDownLatch.countDown();
                        System.out.println("当前线程为---- --  >>>>>>>> " + Thread.currentThread().getName() + " 数量剩余：" + countDownLatch.getCount());
                    }
                }));
            }
            countDownLatch.await();
            long endDate = System.currentTimeMillis();
            System.out.println("执行消耗时间  ------ >>>>>" + (endDate - startDate));
        } catch (Exception ex) {

        }
    }
}
