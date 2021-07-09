package com.example.hello.controller;

import com.example.hello.model.Student;
import com.example.hello.service.IStudentRankRating;
import com.example.hello.service.IStudentSortingAlgo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentRankRating studentRankRating;
    @Autowired
    private IStudentSortingAlgo studentSortingAlgo;

    @GetMapping("/listByRank")
    public Map<String, List<Student>> getAllByRank() {
        List<Student> students = generateRandomStudents();
        Map<String, List<Student>> studentRankingListByRank = students.stream().collect(Collectors.groupingBy(studentRankRating));
        for (String key : studentRankingListByRank.keySet()) {
            List<Student> studentSubList = studentRankingListByRank.get(key);
            Collections.sort(studentSubList, studentSortingAlgo);
            studentRankingListByRank.put(key, studentSubList);
        }
        return studentRankingListByRank;
    }

    private List<Student> generateRandomStudents() {
        List<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            Student student = new Student();
            student.setName("student_" + i);
            student.setNo(i);
            student.setScore(random.nextInt(100));
            students.add(student);
        }
        return students;
    }
}
