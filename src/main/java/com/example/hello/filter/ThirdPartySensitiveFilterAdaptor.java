package com.example.hello.filter;

public class ThirdPartySensitiveFilterAdaptor extends ThirdPartySensitiveWordFilter implements ISensitiveWordFilter{
    @Override
    public String filter(String text) {
        super.pornoWordFilter(text);
        super.politicalWordFilter(text);
        return text;
    }
}
