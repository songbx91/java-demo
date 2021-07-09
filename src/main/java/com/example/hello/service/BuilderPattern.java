package com.example.hello.service;

import com.example.hello.model.Rectangle;

public class BuilderPattern {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle.Builder()
                .setName("Rectangle1")
                .setWidth(100)
                .setLength(200)
                .build();
    }
}

