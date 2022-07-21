package com.jansora.demo.utils;

import com.jansora.demo.infrastructure.function.DoSomething;
import com.jansora.demo.infrastructure.function.DoSomethingWithThrowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * <Description> Description for Cost <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/12 PM02:32 <br>
 * @since 1.0 <br>
 */
public final class Cost {

    private static final Logger LOGGER = LoggerFactory.getLogger(Cost.class);
    private static final String COST_PREFIX = "[ --- COST --- ] ";

    private Cost() {

    }

    /**
     * 计时器
     */
    public static void timeWithEx(String moduleName, DoSomethingWithThrowable doSomething) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            String name = StringUtils.hasLength(moduleName) ? moduleName : "default-" + DateUtils.formatNow();
            LOGGER.debug(COST_PREFIX + moduleName + " cost start ");
            long start = System.nanoTime();
            doSomething.doSomething();
            float cost = (float) (System.nanoTime() - start) / 1000_000;
            LOGGER.debug(COST_PREFIX + name + " cost end. " + "[ cost: " + cost + " ms. ] ");
            return;
        }

        doSomething.doSomething();

    }

    /**
     * 计时器
     */
    public static void timeWithEx(DoSomethingWithThrowable doSomething) throws Throwable {
        if (LOGGER.isDebugEnabled()) {
            String moduleName = "default-" + DateUtils.formatNow();
            timeWithEx(moduleName, doSomething);
            return;
        }
        doSomething.doSomething();
    }


    /**
     * 计时器
     */
    public static void time(String moduleName, DoSomething doSomething) {
        if (LOGGER.isDebugEnabled()) {
            String name = StringUtils.hasLength(moduleName) ? moduleName : "default-" + DateUtils.formatNow();
            LOGGER.debug(COST_PREFIX + moduleName + " cost start ");
            long start = System.nanoTime();
            doSomething.doSomething();
            float cost = (float) (System.nanoTime() - start) / 1000_000;
            LOGGER.debug(COST_PREFIX + name + " cost end. " + "[ cost: " + cost + " ms. ] ");
            return;
        }

        doSomething.doSomething();

    }

    /**
     * 计时器
     */
    public static void time(DoSomething doSomething) {
        if (LOGGER.isDebugEnabled()) {
            String moduleName = "default-" + DateUtils.formatNow();
            time(moduleName, doSomething);
            return;
        }
        doSomething.doSomething();
    }


}
