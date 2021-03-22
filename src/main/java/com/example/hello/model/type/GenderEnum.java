package com.example.hello.model.type;

import lombok.Getter;

@Getter
public enum GenderEnum implements IBaseEnum<Integer> {
    UNKNOWN(0, "保密"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private Integer index;
    private String value;

    GenderEnum(Integer index, String value) {
        this.index = index;
        this.value = value;
    }
}
