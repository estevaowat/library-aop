package com.ewcode.securityitau.services;

import com.ewcode.securityitau.annotation.MeasureTime;
import com.ewcode.securityitau.annotation.ValidateData;

import java.util.logging.Logger;

public class Service {

    private static final Logger logger = Logger.getLogger(Service.class.getName());

    @MeasureTime
    public int sumTwoNumbers(int number1, int number2) {
        return number1 + number2;
    }

    @ValidateData
    public String process(String data) {
        logger.info("Starting Service.process");
        return data + " World";
    }

}
