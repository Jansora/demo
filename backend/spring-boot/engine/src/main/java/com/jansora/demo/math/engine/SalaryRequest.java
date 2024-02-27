package com.jansora.demo.math.engine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-26 10:59:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRequest implements Serializable  {

    /**
     * 变量
     */
    Map<String, String> variables;

    /**
     * 方法
     */
    Map<String, String> functions;

    /**
     * 基础工资 (税前)
     */
    String finalFormula;

    /**
     * 公式
     */
    Map<String, String> formulas;

}
