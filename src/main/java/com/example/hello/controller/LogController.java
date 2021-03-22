package com.example.hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Log")
public class LogController {
    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
    private ConditionContext context;

    @GetMapping("/info")
    public void info() {
        logger.info("os.name:" + context.getEnvironment().getProperty("os.name"));
    }

    @GetMapping("/error")
    public void error() {
        logger.error("Oops, something wrong happened!!!!!");
    }
}
