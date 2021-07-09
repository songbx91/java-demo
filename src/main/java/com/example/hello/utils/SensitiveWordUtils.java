package com.example.hello.utils;

import com.example.hello.filter.ISensitiveWordFilter;

import java.util.ArrayList;
import java.util.List;

public class SensitiveWordUtils {
    private List<ISensitiveWordFilter> filters = new ArrayList<>();

    public void addSensitiveWordFilter(ISensitiveWordFilter filter) {
        filters.add(filter);
    }

    public String wordCheck(String text) {
        String maskedText = text;
        for (ISensitiveWordFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}
