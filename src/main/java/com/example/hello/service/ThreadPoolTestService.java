package com.example.hello.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTestService {
    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("当前线程名字：" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Task());
        }
        System.out.println(Thread.currentThread().getName());
    }
}
