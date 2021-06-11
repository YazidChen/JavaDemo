package com.yazid.demo.java.thread;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.concurrent.*;

/**
 * @author YazidChen
 * @date 2020/02/12 0012 16:28
 */
public class ThreadTest {
    private static ThreadPoolExecutor executor;

    @BeforeAll
    public static void beforeConcurrent() {
        if (executor == null) {
            System.out.println("beforeConcurrent");
            executor = new ThreadPoolExecutor(4, 8, 60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(4));
        }
    }

    @RepeatedTest(1000)
    @Execution(ExecutionMode.CONCURRENT)
    void concurrentTest() {
        executor.execute(() -> {
            try {
                System.out.println("ThreadId:" + Thread.currentThread().getId() + "/ThreadName:" + Thread.currentThread().getName());
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 创建线程
     */
    @Test
    public void createThreadTest() {
        //1.继承Thread
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("继承Thread");
                super.run();
            }
        };
        thread.start();

        //2.实现runable接口
        Thread runnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现runable接口");
            }
        });
        runnableThread.start();

        Thread lambdaRunnableThread = new Thread(() -> System.out.println("实现runable接口lambda简化"));
        lambdaRunnableThread.start();

        //3.实现callable接口
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<String> future = service.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "通过实现Callable接口";
            }
        });
        try {
            String result = future.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService lambdaService = Executors.newSingleThreadExecutor();
        Future<String> lambdaFuture = lambdaService.submit((Callable) () -> "通过实现Callable接口lambda简化");
        try {
            String result = lambdaFuture.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
