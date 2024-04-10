package com.manatee.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.manatee"})
@SpringBootApplication
public class ManateeDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManateeDemoApplication.class, args);
    }

}
