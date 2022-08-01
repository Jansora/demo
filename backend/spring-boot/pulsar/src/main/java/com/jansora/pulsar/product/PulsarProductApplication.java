package com.jansora.pulsar.product;


import com.jansora.app.repo.core.utils.CostUtils;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PulsarProductApplication implements ApplicationRunner {

    @Autowired
    ProductDemo productDemo;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(PulsarProductApplication.class, args);




    }

    @RequestMapping("tt")
    public void tt() throws Throwable {
        while (true) {
            try {
                CostUtils.timeWithEx(() -> productDemo.doSomething(new String[]{}).doSomething());
                ;
            }
            catch (Throwable e) {
                throw new RuntimeException(e);
            }
            Thread.sleep(10000);
        }
    }

    @SafeVarargs
    private static <T extends AbstractDemoFactory> void run(String[] args, Class<T>... demos) throws Throwable {
        for (Class<T> demo : demos) {
            CostUtils.timeWithEx(demo.getName(), () -> demo.newInstance().run(args));
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        while (true) {
//            try {
//                PulsarProductApplication.run(args.getSourceArgs(), ProductDemo.class);
//            }
//            catch (Throwable e) {
//                throw new RuntimeException(e);
//            }
//            Thread.sleep(1000);
//        }

    }
}
