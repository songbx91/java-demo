package com.example.hello.filter;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CustomSensitiveWordFilter {
    private List<String> pornoWords = Arrays.asList("dick", "cock", "tits", "pussy", "penis", "sex");
    private List<String> politicalWords = Arrays.asList("CIA", "FBI");

    public String pornWordsFilter(String text) {
        String lowerCaseText = text.toLowerCase();
        for (String word:pornoWords) {
            if (lowerCaseText.contains(word)) {
                text = text.replaceAll(word, "****");
            }
        }
        return text;
    }

    public String politicalWordsFilter(String text) {
        String upperCaseText = text.toUpperCase();
        for (String word:politicalWords) {
            if (upperCaseText.contains(word)) {
                text = text.replaceAll(word, "####");
            }
        }
        return text;
    }
}
