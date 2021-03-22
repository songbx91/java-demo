package com.example.hello.resource;

import io.jsonwebtoken.Claims;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckResult {
    private boolean success;
    private Claims claims;
    private String errCode;
}
