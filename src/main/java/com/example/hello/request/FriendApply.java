package com.example.hello.request;

import lombok.Data;

@Data
public class FriendApply {
    long userId;
    long targetUserId;
}
