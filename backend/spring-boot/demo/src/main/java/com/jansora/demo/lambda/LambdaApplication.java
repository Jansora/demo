package com.jansora.demo.lambda;


import com.jansora.demo.lambda.lib.LambdaSetDemo;
import com.jansora.repo.demo.utils.DemoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LambdaApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(LambdaApplication.class, args);

//        DemoUtils.run(args, LambdaDemo.class);
        DemoUtils.run(args, LambdaSetDemo.class);
    }


}
