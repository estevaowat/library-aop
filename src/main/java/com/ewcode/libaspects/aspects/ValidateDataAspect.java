package com.ewcode.libaspects.aspects;

import com.ewcode.libaspects.interfaces.IValidateDataService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class ValidateDataAspect {

    private static final String PREFFIX = "[VALIDATE-DATA-ASPECT] => ";
    private static final Logger logger = Logger.getLogger(ValidateDataAspect.class.getName());

    IValidateDataService validateDataService;

    public ValidateDataAspect(IValidateDataService validateDataService) {
        this.validateDataService = validateDataService;
    }

    @Around("@annotation(com.ewcode.libaspects.annotation.ValidateData)")
    public Object validateData(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info(PREFFIX + "Starting validate data");
        String data = (String) joinPoint.getArgs()[0];

        List<String> errors = validateDataService.validateData(data);
        String errorsSizeLog = "errors = " + errors.size();

        logger.info(errorsSizeLog);

        if(errors.isEmpty()) {
            logger.info(PREFFIX + "Data validated");


            return joinPoint.proceed();
        }
        return null;
    }
}
