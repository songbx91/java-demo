package com.example.hello.filter;

import org.springframework.stereotype.Component;

@Component
public class ThirdPartySensitiveWordFilter {
    public void pornoWordFilter(String text) {
        System.out.println("第三方涉黄词汇过滤:" + text);
    }

    public void politicalWordFilter(String text) {
        System.out.println("第三方涉政敏感词过滤:" + text);
    }
}
