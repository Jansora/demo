package com.jansora.demo;

import com.jansora.demo.overbean.OverBeanDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jansora")
public class DemoApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(DemoApplication.class, args);

        new OverBeanDemo().run(args);
    }

}
