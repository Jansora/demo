package com.jansora.demo.annotation;


import com.jansora.demo.annotation.lib.AnnotationOrderDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnnotationApplication implements ApplicationRunner {

    @Autowired
    AnnotationOrderDemo annotationOrderDemo;

    private String[] args;

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(AnnotationApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.args = args.getSourceArgs();
        System.out.println("testABeforeB: ");
        annotationOrderDemo.testABeforeB();
        System.out.println("testAAfterB: ");
        annotationOrderDemo.testAAfterB();
    }
}
