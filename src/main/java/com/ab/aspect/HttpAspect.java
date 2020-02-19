package com.ab.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ab
 * @description aspect
 * @date ${dateTime}
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {
    @Pointcut("execution(public * com.ab.controller.*.*(..))")
    public void log() {
    }

    @Before("log()")
    public void deoBefore(JoinPoint joinPoint) {
        log.info("方法执行前...");
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        log.info("url:" + request.getRequestURI());
        log.info("ip:" + request.getRemoteHost());
        log.info("method:" + request.getMethod());
        log.info("class_method:" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("args:" + joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("方法执行后...");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturning(Object result) {
        log.info("执行返回值：" + result);
    }
}
