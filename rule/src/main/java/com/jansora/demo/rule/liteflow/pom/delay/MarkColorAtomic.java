package com.jansora.demo.rule.liteflow.pom.delay;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 11:32:45
 */
@LiteflowComponent("markColor")
public class MarkColorAtomic extends NodeComponent {
    @Override
    public void process() throws Exception {
        DelayProjectContext context = this.getContextBean(DelayProjectContext.class);
        System.out.println("将项目 [ " + context.getProjectName() + " ] 标记为: " + context.getColor());
    }
}

