package com.example.hello.model;

public class Person {
    private int age;
    private String name;
    private String job;
    private String education;

    public Person () {
    }

    public Person (int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person (int age, String name, String job) {
        this.age = age;
        this.name = name;
        this.job = job;
    }

    protected Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public Person (int age, String name, String job, String education) {
        this.age = age;
        this.name = name;
        this.job = job;
        this.education = education;
    }

    private Person(String name, String job, String education) {
        this.name = name;
        this.job = job;
        this.education = education;
    }
}
