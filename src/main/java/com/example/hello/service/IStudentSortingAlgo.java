package com.example.hello.service;

import com.example.hello.model.Student;

import java.util.Comparator;

public abstract class IStudentSortingAlgo implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return getStudentPriority(o2) - getStudentPriority(o1);
    }

    protected abstract int calculateStudentPriority(Student student);

    private int getStudentPriority(Student student) {
        return calculateStudentPriority(student);
    }
}
