package com.jansora.demo;

import com.jansora.infrastructure.factory.AbstractDemoFactory;
import com.jansora.utils.Cost;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jansora")
public class DemoApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(DemoApplication.class, args);

//        run(args, OverBeanDemo.class);
//        run(args, LambdaDemo.class);


    }

    @SafeVarargs
    private static <T extends AbstractDemoFactory> void run(String[] args, Class<T>... demos) throws Throwable {
        for (Class<T> demo : demos) {
            Cost.timeWithEx(demo.getName(), () -> demo.newInstance().run(args));
        }
    }

}
