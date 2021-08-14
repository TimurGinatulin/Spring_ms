package ru.ginatulin.products.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ProductRequestAnalyzer {
    private Map<Long, Long> productStatistic = new HashMap<>();

    @Before("execution(public * ru.ginatulin.products.controller.v1.ProductRestController.getAllOrder(Long))")
    public void ProductWatcher(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object o : args) {
            if (o != null) {
                if (productStatistic.containsKey(o))
                    productStatistic.replace(Long.parseLong(o.toString()), productStatistic.get(o) + 1);
                else productStatistic.put(Long.parseLong(o.toString()), 1l);
                System.out.println(productStatistic.toString());
            }
        }
    }
}
