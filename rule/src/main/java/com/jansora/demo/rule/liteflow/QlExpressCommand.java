package com.jansora.demo.rule.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class QlExpressCommand implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public void run(String... args) throws Exception {
//        while(true){
//            ExpressRunner runner = new ExpressRunner();
//            com.ql.util.express.DefaultContext<String, Object> context = new DefaultContext<String, Object>();
//            context.put("a", 1);
//            context.put("b", 2);
//            context.put("c", 3);
//            String express = "a + b * c";
//            Object r = runner.execute(express, context, null, true, false);
//            System.out.println(r);
//        }
    }
}
