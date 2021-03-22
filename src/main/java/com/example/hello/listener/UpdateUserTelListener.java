package com.example.hello.listener;

import com.example.hello.event.UpdateUserTelEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserTelListener {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserTelListener.class);
    @EventListener
    @Async
    public void handler(UpdateUserTelEvent event) {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("user tel updated:" + event.getUserTel());
        log.info("Thread Name:" + Thread.currentThread().getName());
    }
}
