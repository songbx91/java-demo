package com.example.hello.repository;

import com.example.hello.model.Article;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
//@Repository
public interface ArticleRepository extends CrudRepository<Article, String> {
    List<Article> findAllByAuthor(String author);
    void deleteAllByAuthor(String author);
    List<Article> findByContent(String content);
}
