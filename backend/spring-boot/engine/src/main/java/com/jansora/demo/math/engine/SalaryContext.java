package com.jansora.demo.math.engine;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-22 17:30:29
 */
public class SalaryContext {



    public static void test(float base) throws Exception {
        ExpressRunner runner = new ExpressRunner(false, false);

        runner.addMacro("试用期", " ( 0.8 ) ");
        runner.addMacro("公积金", " ( salary.base * 0.12 ) ");
        runner.addMacro("绩效系数", " ( 0.70 ) ");
        runner.addMacro("绩效工资", " ( salary.base * ( 1 - 绩效系数) * 0.70 ) ");
        runner.addMacro("加班工资", " ( 500 * 3 ) ");
        
        String express = "" +
                " salary.intermediate = salary.intermediate * 试用期 * 绩效系数;" +
                " salary.intermediate = salary.intermediate - 公积金;" +
                " salary.intermediate = salary.intermediate + 绩效工资;" +
                " salary.intermediate = salary.intermediate + 加班工资;" +
                " return salary;";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();

        Salary salary = new Salary(base);
        context.put("salary", salary);
        Object result = runner.execute(express, context, null, false, true);

        System.out.println(result);

        System.out.println(context);
    }
}
