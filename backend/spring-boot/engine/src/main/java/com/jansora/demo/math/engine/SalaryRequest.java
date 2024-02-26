package com.jansora.demo.math.engine;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-26 10:59:26
 */
@Data
public class SalaryRequest implements Serializable  {


    /**
     * 方法
     */
    List<List<String>> functions;

    /**
     * 基础工资 (税前)
     */
    float base;

    /**
     * 公式
     */
    List<FormulaStack> formula;

    @Data
    public static class FormulaStack implements Serializable {
        /**
         * 块名称
         */
        String name;

        /**
         * 工时
         */
        String formula;
    }
}
