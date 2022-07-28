package com.jansora.demo.lambda.lib;

import com.jansora.app.repo.core.function.DoSomething;
import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.app.repo.core.utils.CostUtils;
import com.jansora.repo.demo.AbstractDemoFactory;
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

                CostUtils.time("normal 1000 0000", () -> {
                    for (int i = 0; i < 1_000_000; i++) {

                    }
                });

                CostUtils.time("normal 1000 0000", () -> {
                    for (int i = 0; i < 1_000_000; i++) {
                        doSomething.doSomething();
                    }
                });
            }

        };
    }
}
