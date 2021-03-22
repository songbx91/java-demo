package com.example.hello.repository;

import com.example.hello.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>  {
    Optional<User> findByToken(String token);
}
