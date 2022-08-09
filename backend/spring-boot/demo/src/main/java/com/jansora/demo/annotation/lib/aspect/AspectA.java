package com.jansora.demo.annotation.lib.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class AspectA {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectA.class);


    @Around("@annotation(com.jansora.demo.annotation.lib.annotation.B)")
    public Object b(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("aspect b.");
        return proceedingJoinPoint.proceed();
    }

    @Around("@annotation(com.jansora.demo.annotation.lib.annotation.A)")
    public Object a(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        LOGGER.debug("aspect a.");
        return proceedingJoinPoint.proceed();
    }

}
