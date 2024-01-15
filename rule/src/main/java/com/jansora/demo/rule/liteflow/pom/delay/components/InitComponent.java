package com.jansora.demo.rule.liteflow.pom.delay.components;

import com.jansora.demo.rule.liteflow.pom.delay.DelayProjectContext;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 11:32:45
 */
@LiteflowComponent("init")
public class InitComponent extends NodeComponent {
    @Override
    public void process() throws Exception {
        DelayProjectContext context = this.getContextBean(DelayProjectContext.class);
        System.out.println("流程初始化...");
    }
}

