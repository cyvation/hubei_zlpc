/*
package com.start.boot.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class WebAspect {


    @Pointcut("execution(* com.start.boot.web..*.*(..))")
    public void point(){}


   // @Around("point()")
    public void before(ProceedingJoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        */
/**
         * 记录请求的信息
         *//*

        String remoteAddr = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String remoteUser = request.getRemoteUser();
        int remotePort = request.getRemotePort();
    }


}
*/
