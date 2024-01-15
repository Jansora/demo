package com.jansora.demo.liteflow.components;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2023-11-27 14:47:23
 */
@LiteflowComponent("initial-component")
public class InitializeAccountComponent extends NodeComponent {


    @Autowired
    FlowExecutor executor;


    @Override
    public void process() throws Exception {

        System.out.println("do initial account action.");

    }
}
