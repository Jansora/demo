package com.jansora.demo.rule.liteflow;

import com.yomahub.liteflow.core.FlowExecutor;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LiteFlowCommand implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public void run(String... args) throws Exception {
//        while(true){
//            DelayProjectContext context = new DelayProjectContext();
//            context.setProjectName("流程引擎测试");
//            context.setCompletedDate(LocalDateTime.now());
//            context.setExpectedDate(LocalDateTime.now().minusDays(15L));
//            // 预期 && 完成时间
//            LocalDateTime expected = context.getExpectedDate();
//            LocalDateTime completed = context.getCompletedDate();
//
//            LiteflowResponse response = flowExecutor.execute2Resp("chain1", null, context);
////            DefaultContext context = response.getFirstContextBean();
////            System.out.println(JsonUtil.toJsonString(context.getData("student")));
//            if (response.isSuccess()){
//                log.info("执行成功");
//            }else{
//                log.info("执行失败");
//            }
//            Thread.sleep(1000);
//        }
    }
}
