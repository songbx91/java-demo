package com.example.hello.repository;

import com.example.hello.model.HelloModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface HelloRepository extends JpaRepository<HelloModel, Integer> {
}
