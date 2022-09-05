package com.jansora.demo.concurrent;


import com.jansora.demo.concurrent.lib.ThreadDemo;
import com.jansora.repo.demo.utils.DemoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcurrentApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(ConcurrentApplication.class, args);

        DemoUtils.run(args, ThreadDemo.class);

    }


}
