package com.jansora.demo.rule.liteflow.pom.delay.components.colorize;

import com.jansora.demo.rule.liteflow.pom.delay.DelayProjectContext;
import com.jansora.demo.rule.pom.Project;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 11:32:45
 */
@LiteflowComponent("red")
public class MarkRedComponent extends NodeComponent {
    @Override
    public void process() throws Exception {
        DelayProjectContext context = this.getContextBean(DelayProjectContext.class);
        context.setColor(Project.Color.RED.name());
        System.out.println("将项目 [ " + context.getProjectName() + " ] 标记为: " + context.getColor());
    }
}

