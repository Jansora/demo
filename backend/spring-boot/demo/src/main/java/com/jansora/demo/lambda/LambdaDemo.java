package com.jansora.demo.lambda;

import com.jansora.infrastructure.factory.AbstractDemoFactory;
import com.jansora.infrastructure.function.DoSomething;
import com.jansora.infrastructure.function.DoSomethingWithThrowable;
import com.jansora.utils.Cost;

/**
 * <Description> Description for LambdaDemo <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/21 PM03:19 <br>
 * @since 1.0 <br>
 */
public class LambdaDemo extends AbstractDemoFactory {

    private static final DoSomething doSomething = () -> {

    };

    @Override
    protected DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return () -> {
            // call 10 times
            for (int j = 0; j < 10; j++) {

                Cost.time("normal 1000 0000", () -> {
                    for (int i = 0; i < 1_000_000; i++) {

                    }
                });

                Cost.time("normal 1000 0000", () -> {
                    for (int i = 0; i < 1_000_000; i++) {
                        doSomething.doSomething();
                    }
                });
            }

        };
    }
}
