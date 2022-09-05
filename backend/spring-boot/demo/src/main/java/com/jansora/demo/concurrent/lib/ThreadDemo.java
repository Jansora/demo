package com.jansora.demo.concurrent.lib;

import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <Description> Description for OverBeanDemo <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/21 AM09:27 <br>
 * @since 1.0 <br>
 */
@Component
public class ThreadDemo extends AbstractDemoFactory implements ApplicationContextAware {

    private static ApplicationContext context;



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return () -> {
            Thread thread = new Thread();
            thread.wait();

        };
    }
}
