package com.ewcode.securityitau.aspects;

import com.ewcode.securityitau.interfaces.IValidateDataService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.List;
import java.util.logging.Logger;

@Aspect
public class ValidateDataAspect {

    private static final String PREFFIX = "[VALIDATE-DATA-ASPECT] => ";
    private static final Logger logger = Logger.getLogger(ValidateDataAspect.class.getName());

    IValidateDataService validateDataService;

    public ValidateDataAspect(IValidateDataService validateDataService) {
        this.validateDataService = validateDataService;
    }

    @Around("@annotation(com.ewcode.securityitau.annotation.ValidateData)")
    public Object validateData(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(PREFFIX + "Starting validate data");
        String data = (String) joinPoint.getArgs()[0];

        List<String> errors = validateDataService.validateData(data);
        logger.info("errors = " + errors.size());

        if(errors.isEmpty()) {
            logger.info(PREFFIX + "Data validated");
            return joinPoint.proceed();
        }
        return null;
    }
}
