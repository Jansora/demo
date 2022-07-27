package com.jansora.jdk17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class Jdk17Application {

    public static void main(String[] args) {
        var a = new ArrayList<>();
        SpringApplication.run(Jdk17Application.class, args);
    }

}
