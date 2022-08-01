package com.jansora.pulsar.consumer;


import com.jansora.app.repo.core.utils.CostUtils;
import com.jansora.pulsar.product.ProductDemo;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PulsarConsumerApplication {

    @Autowired
    ConsumerDemo consumerDemo;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(PulsarConsumerApplication.class, args);

//        run(args, ConsumerDemo.class);

    }

    @SafeVarargs
    private static <T extends AbstractDemoFactory> void run(String[] args, Class<T>... demos) throws Throwable {
        for (Class<T> demo : demos) {
            CostUtils.timeWithEx(demo.getName(), () -> demo.newInstance().run(args));
        }
    }

}
