package com.example.hello.controller;

import com.example.hello.model.Article;
import com.example.hello.model.Rectangle;
import com.example.hello.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleRepository articleRepository;

    @PostMapping("/create")
    public void create(@RequestParam("author") String author,
                       @RequestParam("content") String content,
                       @RequestParam("type") String type) {
        Article article = new Article();
        article.setSk((System.currentTimeMillis() / 1000) + "");
        article.setContent(content);
        article.setAuthor(author);
        article.setType(type);

        articleRepository.save(article);
    }

    @GetMapping("/createRectangle")
    public void createRectangle(@RequestParam("name") String name,
                                @RequestParam("width") Integer width,
                                @RequestParam("length") Integer length) {
        new Rectangle.Builder().setName("rectangle" + name)
                .setWidth(width)
                .setLength(length)
                .build();
    }

    @GetMapping("/list-by-author")
    public List<Article> listByAuthor(@RequestParam("author") String author) {
        return articleRepository.findAllByAuthor(author);
    }

    @GetMapping("/list-by-content")
    public List<Article> listByContent(@RequestParam("content") String content) {
        return articleRepository.findByContent(content);
    }

    @DeleteMapping("/del-by-author")
    public void delByAuthor(@RequestParam("author") String author) {
        articleRepository.deleteAllByAuthor(author);
    }
}
