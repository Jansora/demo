package com.jansora.demo.java;


import com.jansora.app.repo.core.generator.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(nameGenerator = CustomBeanNameGenerator.class)
public class ReorderApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(ReorderApplication.class, args);

    }

}
