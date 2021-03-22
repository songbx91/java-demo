package com.example.hello.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="momento")
@PropertySource(value="classpath:momento.properties",encoding = "utf-8", ignoreResourceNotFound = true)
public class MomentoConfig {
    private Integer matchRate;
}
