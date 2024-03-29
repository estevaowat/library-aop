package com.ewcode.libaspects;

import com.ewcode.libaspects.aspects.PerformanceAspect;
import com.ewcode.libaspects.services.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

class PerformanceAspectTest {

    @Test
    void logExecutionTimeAroundTest() {
        AspectJProxyFactory factory = new AspectJProxyFactory(new Service());
        PerformanceAspect aspect = new PerformanceAspect();
        factory.addAspect(aspect);
        Service proxy = factory.getProxy();

        int result = proxy.sumTwoNumbers(1, 2);
        Assertions.assertEquals(3, result);
    }
}
