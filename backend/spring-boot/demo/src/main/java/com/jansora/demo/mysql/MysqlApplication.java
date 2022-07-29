package com.jansora.demo.mysql;


import com.jansora.demo.mysql.lib.MysqlDemo;
import com.jansora.repo.demo.utils.DemoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MysqlApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(MysqlApplication.class, args);

        DemoUtils.run(args, MysqlDemo.class);

    }


}
