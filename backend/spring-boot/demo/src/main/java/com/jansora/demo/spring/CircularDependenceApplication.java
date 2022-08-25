package com.jansora.demo.spring;


import com.jansora.app.repo.core.generator.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(nameGenerator = CustomBeanNameGenerator.class)
public class CircularDependenceApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(CircularDependenceApplication.class, args);

    }

}
