package com.example.hello.service;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo implements Runnable {
    boolean done = false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r= new VolatileDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(((VolatileDemo)r).done);
        System.out.println(((VolatileDemo)r).realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    public void setDone() {
        done = true;
    }
}
