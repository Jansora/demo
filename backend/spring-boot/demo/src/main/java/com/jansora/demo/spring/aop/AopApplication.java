package com.jansora.demo.spring.aop;


import com.jansora.app.repo.core.generator.CustomBeanNameGenerator;
import com.jansora.demo.spring.aop.lib.BeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(nameGenerator = CustomBeanNameGenerator.class)
public class AopApplication implements ApplicationRunner {

    @Autowired
    BeanA a;

    public static void main(String[] args) throws Throwable {

        SpringApplication.run(AopApplication.class, args);

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        a.b();
        a.a();
    }
}
