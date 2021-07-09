package com.example.hello.service;

import com.example.hello.model.Student;

import java.util.function.Function;

public interface IStudentRankRating extends Function<Student, String> {
    @Override
    String apply(Student student);
}
