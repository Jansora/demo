package com.jansora.demo.rule.liteflow.pom.delay.components;

import com.jansora.demo.rule.liteflow.pom.delay.DelayProjectContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 11:30:58
 */
@LiteflowComponent("delayRule")
public class DelayRuleComponent extends NodeSwitchComponent {
    @Override
    public String processSwitch() throws Exception {
        DelayProjectContext context = this.getContextBean(DelayProjectContext.class);

        // 预期 && 完成时间
        LocalDateTime expected = context.getExpectedDate();
        LocalDateTime completed = context.getCompletedDate();

        // delayDays = completed - expected
        long delayDays = Duration.between(completed, expected).toDays();


        // 0 - 30 days
        if (delayDays > 0 && delayDays <= 30) {
            return "blue";
        }

        // 30 - 60 days
        if (delayDays > 30 && delayDays <= 60) {
            return "yellow";
        }
        // 60 - 90 days
        if (delayDays > 60 && delayDays <= 90) {
            return "red";
        }
        // > 90 days
        if (delayDays > 90) {
            return "black";
        }

        //  < 0 day (expected > completed)
        return "default";
    }
}
