package com.jansora.demo.math.engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-22 17:28:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Salary {

    /**
     * 基础工资 (税前)
     * 基准工资 (税前)
     */
    float base;

    /**
     * 中间态的工资
     */
    float intermediate;

    /**
     * 最终工资, 只有在最终确认的时候才会有值
     */
    float finalSalary = 0;

    public Salary(float base) {
        this.base = base;
        this.intermediate = base;
    }


    /**
     * 输出最终金额
     */
    public float finalSalary() {
        if (finalSalary != 0) {
            this.finalSalary = intermediate;
            return finalSalary;
        }
        throw new RuntimeException("最终工资不能为负数");
    }
}
