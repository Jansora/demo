package com.jansora.demo.rule.liteflow.pom.delay.components;

import com.jansora.demo.rule.liteflow.pom.delay.DelayProjectContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeSwitchComponent;

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

        // 0 - 30 days
        if (completed.isAfter(expected) && completed.minusDays(30L).isBefore(expected)) {
            return "blue";
        }

        // 30 - 60 days
        if (completed.minusDays(30L).isAfter(expected) && completed.minusDays(60L).isBefore(expected)) {
            return "yellow";
        }
        // 60 - 90 days
        if (completed.minusDays(60L).isAfter(expected) && completed.minusDays(90L).isBefore(expected)) {
            return "red";
        }
        // > 90 days
        if (completed.minusDays(90L).isAfter(expected)) {
            return "black";
        }

        //  < 0 day (expected > completed)
        return "default";
    }
}
