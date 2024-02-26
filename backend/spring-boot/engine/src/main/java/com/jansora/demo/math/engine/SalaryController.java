package com.jansora.demo.math.engine;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.instruction.op.OperatorBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-22 17:30:29
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @PostMapping("/calculate")
    public void calculate(@RequestBody SalaryRequest request) throws Exception {
        test(10000);
    }



    public static void test(float base) throws Exception {
        ExpressRunner runner = new ExpressRunner(false, false);

        runner.addMacro("税前工资", " salary.base ");
        runner.addMacro("当前工资", " salary.currentSalary ");
        runner.addMacro("试用期系数", " ( 0.8 ) ");
        runner.addMacro("公积金", " ( 税前工资 * 0.12 ) ");
        runner.addMacro("绩效系数", " ( 0.70 ) ");
        runner.addMacro("绩效工资", " ( 税前工资 * ( 1 - 绩效系数) * 0.70 ) ");
        runner.addMacro("加班工资", " ( 500 * 3 ) ");
        
        String express = "" +
                "stack = new HashMap();" +
                " 当前工资 = 当前工资 * 试用期系数 * 绩效系数;" +
                " 当前工资 = 当前工资 - 公积金;" +
                " 当前工资 = 当前工资 + 绩效工资;" +
                " 当前工资 = 当前工资 + 加班工资;" +
                "" +
                "map = new HashMap();\n" +
                "stack.put(\"a\", \"a_value\");\n" +
                "stack.put(\"b\", \"b_value\");" +
                "return stack.a;";
        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        OperatorBase op = new ContextMessagePutTest.OperatorContextPut("contextPut");
        runner.addFunction("put", op);
        Salary salary = new Salary(base);
        context.put("salary", salary);

        Object result = runner.execute(express, context, null, false, true);

        System.out.println(result);

        System.out.println(context);
    }
}
