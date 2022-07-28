package com.jansora.demo.lambda;


import com.jansora.app.repo.core.utils.CostUtils;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LambdaApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(LambdaApplication.class, args);

        run(args, LambdaDemo.class);

    }

    @SafeVarargs
    private static <T extends AbstractDemoFactory> void run(String[] args, Class<T>... demos) throws Throwable {
        for (Class<T> demo : demos) {
            CostUtils.timeWithEx(demo.getName(), () -> demo.newInstance().run(args));
        }
    }

}
