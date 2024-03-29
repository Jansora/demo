package com.jansora.demo.overbean;


import com.jansora.app.repo.core.utils.CostUtils;
import com.jansora.demo.overbean.lib.OverBeanDemo;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OverBeanApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(OverBeanApplication.class, args);

        run(args, OverBeanDemo.class);

    }

    @SafeVarargs
    private static <T extends AbstractDemoFactory> void run(String[] args, Class<T>... demos) throws Throwable {
        for (Class<T> demo : demos) {
            CostUtils.timeWithEx(demo.getName(), () -> demo.getDeclaredConstructor().newInstance().run(args));
        }
    }

}
