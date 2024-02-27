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
     * 当前段的工资
     */
    float stackSalary;

    /**
     * 当前工资(中间态的工资)
     */
    float currentSalary;

    /**
     * 最终工资, 只有在最终确认的时候才会有值
     */
    float finalSalary;


    /**
     * 工作日加班天数
     */
    float weekday;

    /**
     * 周末加班天数
     */
    float weekend;

    /**
     * 节假日加班天数
     */
    float holiday;


    public Salary(float base) {
        this.base = base;
        this.currentSalary = base;
    }


    /**
     * 输出最终金额
     */
    public float finalSalary() {
        this.finalSalary = currentSalary;
        return finalSalary;
    }
}
