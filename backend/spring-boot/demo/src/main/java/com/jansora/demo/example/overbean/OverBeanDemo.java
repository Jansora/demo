package com.jansora.demo.example.overbean;

import com.jansora.demo.example.overbean.service.AsianService;
import com.jansora.demo.example.overbean.service.EuropeanService;
import com.jansora.demo.factory.AbstractDemoFactory;
import com.jansora.demo.infrastructure.function.DoSomethingWithThrowable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OverBeanDemo extends AbstractDemoFactory implements ApplicationContextAware {


    private static ApplicationContext context;
    @Autowired
    AsianService asianService;
    @Autowired
    EuropeanService europeanService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return () -> {
            context.getBean(AsianService.class).sayHello();
            context.getBean(EuropeanService.class).sayHello();
        };
    }
}
