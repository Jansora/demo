package com.jansora.demo.liteflow.components;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2023-11-27 15:10:58
 */
//@LiteflowComponent
public class DeclarativeComponents {

    @Autowired
    FlowExecutor executor;

    public void reload() {
        executor.reloadRule();
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeId = "declarative-initial-account", nodeName = "声明式初始化账户方法组件")
    public void customDeclarativeInitialAccountComponentProcess(NodeComponent bindCmp) {
        System.out.println("do declarative initial account action.");
    }

    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS, nodeId = "declarative-binding-phonenumber", nodeName = "声明式绑定手机号方法组件")
    public void customBindingPhonenumberProcess(NodeComponent bindCmp) {
        System.out.println("do declarative initial account action.");
    }
}
