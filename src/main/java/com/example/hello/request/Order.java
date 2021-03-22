package com.example.hello.request;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Integer productId;
    private Double price;
}
