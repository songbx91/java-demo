package com.example.hello.repository;

import com.example.hello.model.UserAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAmountRepository extends JpaRepository<UserAmount, Long>  {
}
