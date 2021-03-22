package com.example.hello.mapper;

import com.example.hello.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface UserMapper {
    @Select("SELECT * from user WHERE name = #{name}")
    List<User> findByName(@Param("name") String name);

    @Select("SELECT * from user WHERE tel = #{tel}")
    List<User> findByTel(@Param("tel") String tel);

    @Select("SELECT * from user WHERE name = #{name} limit 1")
    User getByName(@Param("name") String name);

    @Select("SELECT * from user where tel = #{tel} limit 1")
    User getByTel(@Param("tel") String tel);

    @Select("SELECT * FROM user")
    List<User> getAll();

    @Insert("INSERT INTO user (description,email,name,tel) VALUES (#{description}, #{email}, #{name}, #{tel})")
    void insert(User user);

    @Update("UPDATE user SET tel = #{tel} WHERE id = #{id}")
    void updateTel(User user);

    @Delete("DELETE from user where name = #{name}")
    void deleteByName(@Param("name") String name);

    @Select("SELECT * FROM user where id = #{id} limit 1")
    Optional<User> getById(@Param("id") Integer id);
}
