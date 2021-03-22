package com.example.hello.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "shumei")
@PropertySource(value = "classpath:content_check.properties", encoding = "utf-8")
public class ShumeiConfig {
    private Double sexyConfidenceScore;
    private Double pornConfidenceScore;
    private Double normalConfidenceScore;
    private String secretId;
    private List<Integer> femaleConfirmLabel;
}
