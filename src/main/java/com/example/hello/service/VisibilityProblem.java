package com.example.hello.service;

import java.io.IOException;

public class VisibilityProblem {
    volatile int a = 10;
    volatile int b = 20;

    private static int num1 = 0;

    private void change() {
        a = 30;
        b = a;
    }

    private void print() {
        System.out.println("b=" + b + ", a=" + a);
    }

    public static void incrNum() {
        num1++;
    }

    public int getNum() {
        return num1;
    }

    public static void main(String[] args) throws IOException {
        VisibilityProblem visibilityProblem = new VisibilityProblem();
        MyThread t1 = visibilityProblem.new MyThread();
        MyThread t2 = visibilityProblem.new MyThread();
        MyThread t3 = visibilityProblem.new MyThread();
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
        }

        System.out.println("num value:" + visibilityProblem.getNum());

    }

    class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(20);
                for (int i = 0; i < 100; i++) {
//                System.out.println("Thread:"+ Thread.currentThread().getName());
                    VisibilityProblem.incrNum();
                }
            } catch (InterruptedException e) {
            }

        }
    }
}
