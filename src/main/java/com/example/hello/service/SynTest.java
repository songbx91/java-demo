package com.example.hello.service;

public class SynTest {
    public void syncBlock() {
        synchronized (this) {
            System.out.println("test");
        }
    }
}
