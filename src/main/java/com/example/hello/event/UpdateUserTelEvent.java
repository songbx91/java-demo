package com.example.hello.event;

import com.example.hello.model.User;
import org.springframework.context.ApplicationEvent;

public class UpdateUserTelEvent extends ApplicationEvent {
    private User user;

    public UpdateUserTelEvent(Object source, User user) {
        super(source);
        this.setUser(user);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserTel() {
        return this.user.getTel();
    }
}
