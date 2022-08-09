package com.jansora.demo.annotation.lib;

import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.demo.annotation.lib.annotation.A;
import com.jansora.demo.annotation.lib.annotation.B;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.stereotype.Component;

/**
 * <Description> Description for OverBeanDemo <br>
 * 注解顺序 demo
 */
@Component
public class AnnotationOrderDemo extends AbstractDemoFactory {


    @A
    @B
    public void testABeforeB() {

    }

    @B
    @A
    public void testAAfterB() {

    }

    @Override
    public DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return () -> {
            System.out.println("testABeforeB: ");
            testABeforeB();
            System.out.println("testAAfterB: ");
            testAAfterB();
        };
    }
}
