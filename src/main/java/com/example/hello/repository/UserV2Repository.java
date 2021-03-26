package com.example.hello.repository;

import com.example.hello.model.dynamo.UserV2;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserV2Repository extends CrudRepository<UserV2, String> {
    List<UserV2> findByLastName(String lastName);
    List<UserV2> findByFirstName(String firstName);
}
