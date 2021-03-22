package com.example.hello;

import com.example.hello.mapper.UserMapper;
import com.example.hello.model.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloApplication.class)
@FixMethodOrder(MethodSorters.JVM)
public class MybatisTest {
    @Autowired
    UserMapper userMapper;

    private static final Logger logger = LoggerFactory.getLogger(MybatisTest.class);

    @Test
    public void testInsert() {
        User user = new User();
        user.setName("kobe");
        user.setDescription("NBA star");
        user.setEmail("kobe@gmail.com");
        user.setTel("234567");
        userMapper.insert(user);
    }

    @Test
    public void testUpdate() {
        User user = userMapper.getByName("kobe");
        user.setTel("10086");
        userMapper.updateTel(user);
        Assert.assertTrue(userMapper.findByTel("10086").size() == 1);
    }

    @Test
    public void testQuery() {
        logger.info("kobe number:" + userMapper.findByName("kobe").size());
        Assert.assertTrue(userMapper.findByName("kobe").size() > 0);
    }

    @Test
    public void testDelete() {
        userMapper.deleteByName("kobe");
    }
}
