package com.jansora.demo.java.recorder;


import com.jansora.repo.demo.utils.DemoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecorderApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(RecorderApplication.class, args);

        DemoUtils.run(args, RecorderDemo.class);

    }


}
