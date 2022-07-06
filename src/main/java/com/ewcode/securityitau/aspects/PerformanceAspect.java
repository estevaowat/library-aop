package com.ewcode.securityitau.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Aspect
@Component
public class PerformanceAspect {

    private static final String PREFFIX = "[PERFORMANCE-ASPECT] => ";
    private static final Logger logger = Logger.getLogger(PerformanceAspect.class.getName());

    @Around("@annotation(com.ewcode.securityitau.annotation.MeasureTime)")
    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final String startLog = PREFFIX + "Starting measureTime";
        logger.info(startLog);

        Instant start = Instant.now();
        Object proceed = joinPoint.proceed();
        Instant end = Instant.now();

        long durationInMillis = Duration.between(start, end).toMillis();
        String durationLog = PREFFIX + "Duration of method is " + durationInMillis + "ms";

        logger.info(durationLog);

        return proceed;
    }
}
