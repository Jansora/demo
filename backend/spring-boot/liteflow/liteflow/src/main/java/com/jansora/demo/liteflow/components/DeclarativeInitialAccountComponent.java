package com.jansora.demo.liteflow.components;

import com.yomahub.liteflow.annotation.LiteflowCmpDefine;
import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2023-11-27 15:07:17
 */
//@LiteflowCmpDefine
//@LiteflowComponent("declarative-initial-component")
public class DeclarativeInitialAccountComponent{


    @LiteflowMethod(LiteFlowMethodEnum.PROCESS)
    public void customProcess(NodeComponent bindCmp) {
        System.out.println("do declarative initial account action.");
    }

}

