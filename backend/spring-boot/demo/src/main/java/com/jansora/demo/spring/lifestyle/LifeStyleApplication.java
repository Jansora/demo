package com.jansora.demo.spring.lifestyle;


import com.jansora.app.repo.core.generator.CustomBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(nameGenerator = CustomBeanNameGenerator.class)
public class LifeStyleApplication {

    static int row = 1024;
    static int col = 512;
    static int[][] matrix = new int[row][col];

    public static void main(String[] args) throws Throwable {


        SpringApplication.run(LifeStyleApplication.class, args);

    }

}
