package com.example.hello.filter;

public class CustomSensitiveWordFilterAdaptor extends CustomSensitiveWordFilter implements ISensitiveWordFilter {
    @Override
    public String filter(String text) {
        text = super.pornWordsFilter(text);
        text = super.politicalWordsFilter(text);
        return text;
    }
}
