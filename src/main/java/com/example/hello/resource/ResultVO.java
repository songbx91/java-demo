package com.example.hello.resource;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
