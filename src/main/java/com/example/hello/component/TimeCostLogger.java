package com.example.hello.component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class TimeCostLogger {
    private long startTs;
    private long endTs;
    @Pointcut(value = "execution(* com.example.hello.controller.UserController.*(..))")
    private void aspectPointcut() {
    }

    @Before(value = "aspectPointcut()")
    public void aspectBefore(JoinPoint joinPoint) {
        startTs = System.currentTimeMillis();
        System.out.println("开始调用接口:" + startTs);
//        Object[] args = joinPoint.getArgs();
//        Signature signature = joinPoint.getSignature();
//        Object target = joinPoint.getTarget();
//        Object aThis = joinPoint.getThis();
//        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
//        SourceLocation sourceLocation = joinPoint.getSourceLocation();
//        String longString = joinPoint.toLongString();
//        String shortString = joinPoint.toShortString();

//        System.out.println("接口前置");
//        System.out.println("\targs=" + Arrays.asList(args));
//        System.out.println("\tsignature=" + signature);
//        System.out.println("\ttarget=" + target);
//        System.out.println("\taThis=" + aThis);
//        System.out.println("\tstaticPart=" + staticPart);
//        System.out.println("\tsourceLocation=" + sourceLocation);
//        System.out.println("\tlongString=" + longString);
//        System.out.println("\tshortString=" + shortString);
    }
    @After(value = "aspectPointcut()")
    public void aspectAfter(JoinPoint joinPoint) {
        endTs = System.currentTimeMillis();
        System.out.println("调用接口结束:" + endTs);
    }

    @Around(value = "aspectPointcut()")
    public Object aspectAround(ProceedingJoinPoint pjp) throws Throwable {
        Long t1 = System.currentTimeMillis();
        Object res = pjp.proceed();
        Long t2 = System.currentTimeMillis();
        System.out.println("接口调用耗时:" + (t2 - t1) + "ms");
        return res;
    }

}
