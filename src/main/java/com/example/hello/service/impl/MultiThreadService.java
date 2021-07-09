package com.example.hello.service.impl;

public class MultiThreadService implements Runnable {
    private Integer b = 100;
    synchronized void m1() throws InterruptedException {
        b = 1000;
        System.out.println("开始暂停重载1秒:" + b);
//        Thread.sleep(50); //6
        System.out.println("结束重载暂停:" + b);
        System.out.println("重载b=" + b);
        System.out.println("");
    }

    synchronized void m2() throws InterruptedException {
        System.out.println("开始暂停2.5秒:" + b);
        Thread.sleep(25); //5
        b = 2000;
        System.out.println("暂停结束:" + b);
        System.out.println("");
    }

    public static void exec() throws InterruptedException {
        MultiThreadService multiThreadService = new MultiThreadService();
        Thread t = new Thread(multiThreadService);
        System.out.println("开始执行方法1:" + multiThreadService.b);
        t.start();
        System.out.println("开始执行方法2:" + multiThreadService.b);
        multiThreadService.m2();
        System.out.println("开始执行方法3:" + multiThreadService.b);
        System.out.println("exec thread b=" + multiThreadService.b);
    }

    @Override
    public void run() {
        try {
            System.out.println("开始执行重载方法:" + b);
            m1();
        } catch (InterruptedException e) {
            System.out.println("开始抛出异常:" + b);
            e.printStackTrace();
        }
    }
}
