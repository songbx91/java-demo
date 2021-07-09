package com.example.hello.service.impl;

import com.example.hello.model.Student;
import com.example.hello.service.IStudentRankRating;
import org.springframework.stereotype.Service;

@Service
public class StudentRankRatingImpl implements IStudentRankRating {
    @Override
    public String apply(Student student) {
        if (student.getRank() == null) {
            student.setRank(rankRating(student));
        }
        return student.getRank();
    }

    private String rankRating(Student student) {
        Integer score = student.getScore();
        if (score < 60) {
            return "D";
        }
        if (score >= 60 && score < 70) {
            return "C";
        }
        if (score >= 70 && score < 80) {
            return "B";
        }
        if (score >= 80 && score < 90) {
            return "A";
        }
        return "S";
    }
}
