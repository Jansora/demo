package com.jansora.demo.other;

import java.util.Arrays;

public class O1 {
    public static void main(String[] args) {
        TraceChainNodeStage.valueOf('I');
    }
    private enum TraceChainNodeStage {
        Initialization('I');

        private char val;

        TraceChainNodeStage(char val) {
            this.val = val;
        }

        public static TraceChainNodeStage valueOf(char v) {
            for (TraceChainNodeStage stage: TraceChainNodeStage.values()) {
                if (stage.val == v) {
                    return stage;
                }
            }
            throw new IllegalArgumentException("invalid stage value: v" + v);
        }
    }
}
