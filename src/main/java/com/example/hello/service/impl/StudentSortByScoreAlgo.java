package com.example.hello.service.impl;

import com.example.hello.model.Student;
import com.example.hello.service.IStudentSortingAlgo;
import org.springframework.stereotype.Service;

@Service
public class StudentSortByScoreAlgo extends IStudentSortingAlgo {
    @Override
    protected int calculateStudentPriority(Student student) {
        return student.getScore();
    }
}
