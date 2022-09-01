package com.jansora.demo.java.lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 电梯
 * 假设你正在开发电梯的控制调度系统，请设计这个调度系统的控制逻辑，并能够响应以下场景：
 * 客人在第n层按向上、向下的按钮
 * 客人等待电梯到达
 * 系统收到请求，按“就近”原则分配最快到达的电梯
 * 客人进入电梯的时候能够指定目标楼层
 * 在到达目标楼层途中可以响应其他同向的客人
 * 不断轮询，有些已分配目标楼层的电梯在客人等待过程中还可以优化...
 * 注：
 * 请关注调度逻辑，忽略非调度相关的细节（如超重、开关门、停机、停电等）
 * 其他合理的场景不一一举例，思考的全面程度也是考核点之一
 * 以上为产品需求，技术需要思考需求并给出技术解决方案，而不是按照产品需求堆叠代码
 * 【任务1】：请用伪代码设计，只需要写interface并辅助文字说明，最后解释一下整体流程。
 */
public class ElevatorSystem {

    private static final int MAX_FLOOR = 36;

    private static int MIN_FLOOR = 1;
    /**
     * 电梯个数
     */
    private final Elevator[] instances = new Elevator[]{};
    /**
     * 外部调度中的指令
     */
    private final Map<Integer, List<Behavior>> outerDispatchActions = new HashMap<>();

    /**
     * 外部发出调度指定
     *
     * @param floor    按下指定的楼层
     * @param behavior "UP" / "DOWNLOAD" 向上或向下
     */
    public void outAction(int floor, Behavior behavior) {
        List<Behavior> behaviors = outerDispatchActions.getOrDefault(floor, new ArrayList<>());
        // 存入指令到指令集中
        if (!behaviors.contains(behavior)) {
            behaviors.add(behavior);
        }

        // 调度电梯
        dispatch(floor, behavior);

    }

    /**
     * 调度算法
     */
    public void dispatch(int floor, Behavior behavior) {

        // 调度电梯编号
        Elevator toBeDispatch = null;

        // 离得最近的
        int oldest = MAX_FLOOR;
        for (Elevator elevator : instances) {

            // 5 层之内 方向相同, 说明当前电梯合适, 取消调度, 等待电梯自行调度
            // distance 是同方向的距离计算方法
//            if (Objects.equals(elevator.status.toString(), behavior.toString()) && distance(elevator.current - floor ) == 5) {
//                return;
//            }
//            // 记录离得最近的停止状态的电梯作为备用机
//            if (elevator.status == Status.STOPPED && distance(elevator.current - floor ) < oldest) {
//                toBeDispatch = elevator;
//                oldest = distance(elevator.current - floor );
//            }


        }

        // 找到调度电梯, 调度
        if (toBeDispatch != null) {
            toBeDispatch.status = behavior == Behavior.UP ? Status.UP : Status.DOWNLOAD;
        }

        // 没有空余电梯繁忙, 取消调度 等待单个电梯自行调度
    }

    /**
     * 运行
     */
    public void running() throws InterruptedException {

        while (true) {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));

            // 调度运行一个楼层
            dispatchOneFloor();
            // 调度运行方向
            dispatchDirection();

        }
    }

    /**
     * 调度一个楼层
     */
    public void dispatchOneFloor() {

        for (Elevator elevator : instances) {
            // 往上运行
            if (elevator.status == Status.UP) {
                elevator.current += 1;
                // 开门, 移除掉当前楼层对应方向的响应
                elevator.innerDispatchFloors.remove(Integer.valueOf(elevator.current));
                outerDispatchActions.getOrDefault(elevator.current, new ArrayList<>()).remove(Behavior.DOWNLOAD);
            }
            // 往下运行
            else if (elevator.status == Status.DOWNLOAD) {
                elevator.current -= 1;
                // 开门, 移除掉当前楼层对应方向的响应
                elevator.innerDispatchFloors.remove(Integer.valueOf(elevator.current));
                outerDispatchActions.getOrDefault(elevator.current, new ArrayList<>()).remove(Behavior.DOWNLOAD);
            } else if (elevator.status == Status.STOPPED) {
                // do nothing
            }

        }


    }

    /**
     * 调度方向
     */
    public void dispatchDirection() {
        for (Elevator elevator : instances) {
            // 调整方向
            if (elevator.current == MAX_FLOOR) {
                elevator.status = Status.DOWNLOAD;
            }
            if (elevator.current == MIN_FLOOR) {
                elevator.status = Status.UP;
            }

            // 内外部都没有调度指令, 停止状态
            if (elevator.innerDispatchFloors.isEmpty() && outerDispatchActions.isEmpty()) {
                elevator.status = Status.STOPPED;
            }

        }

    }


    /**
     * 电梯外部的调度指定
     */
    private enum Status {
        /**
         * 向上
         */
        UP,
        /**
         * 向下
         */
        DOWNLOAD,
        /**
         * 向下
         */
        STOPPED,
    }

    /**
     * 电梯外部的调度指定
     */
    private enum Behavior {
        /**
         * 向上
         */
        UP,
        /**
         * 向下
         */
        DOWNLOAD;
    }

    /**
     * 电梯
     */
    public class Elevator {

        /**
         * 当前所处的楼层
         */
        private int current;

        /**
         * UP 向上 / DOWNLOAD 向下 / STOPPED 停止
         */
        private Status status;

        /**
         * 内部调度的楼层
         */
        private List<Integer> innerDispatchFloors = new ArrayList<>();

        /**
         * 内部发出调度指定
         *
         * @param floor 按下指定的楼层
         */
        public void innerAction(int floor) {
            // 存入指令到指令集中
            innerDispatchFloors.add(floor);
            // 排序
            innerDispatchFloors.sort(Comparator.comparing(Integer::intValue));

        }
    }
}
