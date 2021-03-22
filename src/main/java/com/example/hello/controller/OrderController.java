package com.example.hello.controller;

import com.example.hello.request.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @PostMapping("/createOrder")
    public String createOrder(@RequestBody Order order){
        System.out.println("order id:" + order.getId()
                + ",product id:" + order.getProductId()
                + ",price:" + order.getPrice()
        );
        return "success123";
    }
}
