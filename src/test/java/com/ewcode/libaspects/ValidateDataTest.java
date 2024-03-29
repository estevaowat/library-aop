package com.ewcode.libaspects;

import com.ewcode.libaspects.aspects.ValidateDataAspect;
import com.ewcode.libaspects.interfaces.IValidateDataService;
import com.ewcode.libaspects.services.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.ArrayList;
import java.util.List;

class ValidateDataTest {

    @Test
    void validateDataWithNoErrorsTest() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new Service());

        IValidateDataService validateDataService = data -> {
            List<String> errors = new ArrayList<>();
            //  errors.add("Id not found");
            return errors;
        };

        ValidateDataAspect aspect = new ValidateDataAspect(validateDataService);
        factory.addAspect(aspect);
        Service proxy = factory.getProxy();

        String result = proxy.process("Hello");

        Assertions.assertEquals("Hello World", result);
    }

    @Test
    void validateDataWithErrorsInValidationTest() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new Service());

        IValidateDataService validateDataService = data -> {
            List<String> errors = new ArrayList<>();
            errors.add("Id not found");
            return errors;
        };

        ValidateDataAspect aspect = new ValidateDataAspect(validateDataService);
        factory.addAspect(aspect);
        Service proxy = factory.getProxy();

        String result = proxy.process("Hello");

        Assertions.assertNull(result);
    }

}
