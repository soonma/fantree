package com.team13.fantree.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Optional;

@Slf4j(topic = "Record Output")
@Aspect
@Component
@RequiredArgsConstructor
public class RecordOutputAop {
    @Pointcut("execution(* com.team13.fantree.controller.LikeController.* (..))")
    public void likeRecordOutput() {}
    @Pointcut("execution(* com.team13.fantree.controller.MailController.* (..))")
    public void mailRecordOutput() {}
    @Pointcut("execution(* com.team13.fantree.controller.CommentController.* (..))")
    public void commentRecordOutput() {}
    @Pointcut("execution(* com.team13.fantree.controller.PostController.* (..))")
    public void postRecordOutput() {}
    @Pointcut("execution(* com.team13.fantree.controller.UserController.* (..))")
    public void userRecordOutput() {}
    @Pointcut("execution(* com.team13.fantree.controller.GlobalExceptionController.* (..))")
    public void exceptionRecordOutput() {}

    @Around("likeRecordOutput()||" +
            "mailRecordOutput()||" +
            "commentRecordOutput()||" +
            "postRecordOutput()||" +
            "userRecordOutput()||" +
            "exceptionRecordOutput()")
    public Object recordout(ProceedingJoinPoint joinPoint) throws Throwable{
        HttpServletRequest request ;
        try {
            request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        } catch (IllegalStateException e) {

            return joinPoint.proceed(joinPoint.getArgs());
        }
        try {
            //joinPoint.getArgs()은 인가 정보가 넘어가는걸 확인
            log.info("무슨 정보가 넘어가는 지 확인 : {} ", Arrays.toString(joinPoint.getArgs()));
            return joinPoint.proceed(joinPoint.getArgs());
        } finally {
            log.info("요청한 URL : {}, 요청한 메서드 : {} ", request.getRequestURL(), request.getMethod());
        }
    }


}
