package com.jansora.demo.math.engine;

import com.ql.util.express.*;
import com.ql.util.express.instruction.op.OperatorBase;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-02-22 14:54:41
 */
public class ContextMessagePutTest {
    static class OperatorContextPut extends OperatorBase {
        public OperatorContextPut(String aName) {
            this.name = aName;
        }

        @Override
        public OperateData executeInner(InstructionSetContext parent, ArraySwap list) throws Exception {
            String key = list.get(0).toString();
            Object value = list.get(1);
            parent.put(key, value);
            return null;
        }
    }




    public static void test() throws Exception {
        ExpressRunner runner = new ExpressRunner(false, true);
        OperatorBase op = new OperatorContextPut("contextPut");
        runner.addFunction("contextPut", op);
        String express = "function add(int a, int b){\n" +
                "    return a + b;\n" +
                "};\n" +
                "\n" +
                "function sub(int a, int b){\n" +
                "    return a - b;\n" +
                "};\n" +
                "\n" +
                "a = 10;\n" +
                "return add(a, 4) + sub(a, 9);";


        IExpressContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("success", "true");
        Object result = runner.execute(express, context, null, false, true);
        System.out.println(result);
        System.out.println(context);
    }
}
