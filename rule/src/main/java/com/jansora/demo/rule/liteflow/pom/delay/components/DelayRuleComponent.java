package com.jansora.demo.rule.liteflow.pom.delay.components;

import com.jansora.demo.rule.liteflow.pom.delay.DelayProjectContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 11:30:58
 */
@LiteflowComponent("delayRule")
public class DelayRuleComponent extends NodeComponent {
    @Override
    public void process() throws Exception {

        DelayProjectContext context = this.getContextBean(DelayProjectContext.class);

    }
}
