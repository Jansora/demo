package com.jansora.demo.spring;


import com.jansora.app.repo.core.generator.CustomBeanNameGenerator;
import com.jansora.app.repo.core.utils.CostUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(nameGenerator = CustomBeanNameGenerator.class)
public class CircularDependenceApplication {

    static int row = 1024;
    static int col = 512;
    static int[][] matrix = new int[row][col];


    @Bean
    public static void main(String[] args) throws Throwable {

        //逐行遍历
        CostUtils.time("逐行遍历", () -> {
            int sum_row = 0;
            for (int _r = 0; _r < row; _r++) {
                for (int _c = 0; _c < col; _c++) {
                    sum_row += matrix[_r][_c];
                }
            }
        });

        //逐列遍历
        CostUtils.time("逐列遍历", () -> {
            int sum_col = 0;
            for (int _c = 0; _c < col; _c++) {
                for (int _r = 0; _r < row; _r++) {
                    sum_col += matrix[_r][_c];
                }
            }
        });


        SpringApplication.run(CircularDependenceApplication.class, args);

    }

}
