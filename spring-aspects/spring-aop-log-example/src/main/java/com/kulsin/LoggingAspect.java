package com.kulsin;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    public static final Logger LOG = LoggerFactory.getLogger(LoggingAspect.class);

    public LoggingAspect() {
    }

    @Before("execution(String print*(*))") // This is the Advice
    public void beforeMethod() {
        LOG.info("Before method execution!");
    }

    @After("execution(String print*(*))") // This is the Advice
    public void afterMethod() {
        LOG.info("After method execution!");
    }

}
