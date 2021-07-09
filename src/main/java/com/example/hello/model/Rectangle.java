package com.example.hello.model;

import org.apache.commons.lang3.StringUtils;

public class Rectangle {
    private String name;
    private Integer width;
    private Integer length;

    private Rectangle(Builder builder) {
        this.name = builder.name;
        this.width = builder.width;
        this.length = builder.length;
    }

    public static class Builder {
        private String name;
        private Integer width;
        private Integer length;

        public Rectangle build() {
            if (StringUtils.isBlank(name)) {
                throw new IllegalArgumentException("名字不能为空");
            }
            if (width <= 0) {
                width = 1;
            }
            if (length <= 0) {
                length = 1;
            }
            if (length < width) {
                Integer tmp = length;
                length = width;
                width = tmp;
            }
            System.out.println("rectangle name:" + name + ", length:" + length + ", width:" + width);
            return new Rectangle(this);
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setWidth(Integer width) {
            this.width = width;
            return this;
        }

        public Builder setLength(Integer length) {
            this.length = length;
            return this;
        }
    }
}
