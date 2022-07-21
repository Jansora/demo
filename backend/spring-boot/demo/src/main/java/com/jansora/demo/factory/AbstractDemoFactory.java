package com.jansora.demo.factory;

import com.jansora.demo.infrastructure.function.DoSomethingWithThrowable;
import com.jansora.demo.infrastructure.function.utils.Cost;

/**
 * <Description> Description for AbstractDemoFactory <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/20 AM09:38 <br>
 * @since 1.0 <br>
 */
public abstract class AbstractDemoFactory implements DemoFactory {

    @Override
    public final void run() throws Throwable {
        Cost.timeWithEx(AbstractDemoFactory.class.getSimpleName(), doSomething());
    }

    public abstract DoSomethingWithThrowable doSomething() throws Throwable;

}
