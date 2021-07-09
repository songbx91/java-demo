package com.example.hello.service.impl;

public class StopThreadService implements Runnable{
    @Override
    public void run() {
        int count = 0;
        while(!Thread.currentThread().isInterrupted() && count < 1000) {
            System.out.println("count==" + (count++));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void exec() throws InterruptedException {
        Thread thread = new Thread(new StopThreadService());
        thread.start();
        Thread.sleep(5);
        thread.interrupt();
    }
}
