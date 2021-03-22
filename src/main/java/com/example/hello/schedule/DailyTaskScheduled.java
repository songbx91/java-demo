package com.example.hello.schedule;

import org.omg.CORBA.portable.UnknownException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DailyTaskScheduled {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    @Scheduled(cron = "* 0/20 * * * ?")
    public void reportCurrentTIme() throws UnknownException {
        Thread current = Thread.currentThread();
        System.out.println("task excute @ 10s rate " + dateFormat.format(new Date()) + ", thread: " + current.getId());
    }
}
