package com.jansora.demo.math.engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.jansora.repo.core.payload.dto.ResultDto;
import com.jansora.repo.core.utils.JsonUtils;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.IExpressContext;
import com.ql.util.express.instruction.op.OperatorBase;
import kotlin.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-22 17:30:29
 */
@Slf4j
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @PutMapping("/calculate")
    public ResultDto<Map<String, Object>> calculate(@RequestBody JsonNode request) throws Exception {

        return ResultDto.SUCCESS(test(request));
    }



    public static Map<String, Object> test(JsonNode request) throws Exception {
        ExpressRunner runner = new ExpressRunner(false, false);

        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        OperatorBase op = new ContextMessagePutTest.OperatorContextPut("contextPut");
        runner.addFunction("put", op);


        request.get("variables").fields().forEachRemaining(entry -> {
            try {
                log.info("上添加变量 key: {}, value: {}", entry.getKey(), entry.getValue().asText());
                context.put(entry.getKey(), entry.getValue().asDouble());
            } catch (Exception e) {
                throw new RuntimeException("解析方法是出现错误");
            }
        });
        request.get("functions").fields().forEachRemaining(entry -> {
            try {
                log.info("添加宏替换 key: {}, value: {}", entry.getKey(), entry.getValue().asDouble());
                runner.addMacro(entry.getKey(), entry.getValue().asText());
            } catch (Exception e) {
                throw new RuntimeException("解析方法时出现错误");
            }
        });

//        String express = "" +
//                "stack = new HashMap();";

        StringBuilder express = new StringBuilder().append("stack = new HashMap();\n");
//                " 当前工资 = 税前工资 * 试用期系数 * 绩效系数;" +
//                " 当前工资 = 当前工资 - 公积金;" +
//                " 当前工资 = 当前工资 + 绩效工资;" +
//                " 当前工资 = 当前工资 + 加班工资;" +
//                "" +
//                "map = new HashMap();\n" +
//                "stack.put(\"a\", \"a_value\");\n" +
//                "stack.put(\"b\", \"b_value\");" +
//                "return stack;";
//
        request.get("formulas").fields().forEachRemaining(entry -> {
            try {
                log.info("添加计算公式模块 key: {}, value: {}", entry.getKey(), entry.getValue().asText());
                express.append("stack.put(\"").append(entry.getKey()).append("\", new HashMap());\n");
                StringBuilder expressStack = new StringBuilder().append(entry.getKey()).append(" = ").append( " 0");
                entry.getValue().fields().forEachRemaining(formula -> {
                    try {

                        String expressRaw = formula.getKey() + " = " + formula.getValue().asText() + "; \n";


                        log.info("添加计算公式段 stack: {} expressRaw: {}", entry.getKey(), expressRaw);
                        express.append(expressRaw).append("stack.get('").append(entry.getKey()).append("').put(\"").append(formula.getKey()).append("\", ").append(formula.getKey()).append(");\n")
                                .append("\n");
                        expressStack.append(" + ").append(formula.getKey());
                    } catch (Exception e) {
                        throw new RuntimeException("添加计算公式时出现错误");
                    }
                });
                expressStack.append(";\n");

                log.info("添加计算公式段 stack: {} expressStack: {}", entry.getKey(), expressStack);
                express.append(expressStack);
            } catch (Exception e) {
                throw new RuntimeException("添加计算公式时出现错误");
            }
        });

        express.append("结果 = ").append(request.get("finalFormula").asText()).append(";\n")
                .append("stack.put(\"结果\", 结果);\n")
                .append("return stack;");

        log.info("添加计算公式模块 express: {}", express);

        String expressString = express.toString();
        Map<String, Object> result = (Map<String, Object>) runner.execute(expressString, context, null, false, true);

        System.out.println(result);

        return result;
//        System.out.println(context);
    }
}
