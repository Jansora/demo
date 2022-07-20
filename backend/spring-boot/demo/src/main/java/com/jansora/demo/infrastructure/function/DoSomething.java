package com.jansora.demo.infrastructure.function;

/**
 * <Description> 与默认的 Function 功能保持一致 <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/12 PM02:22 <br>
 * @since 1.0 <br>
 */
@FunctionalInterface
public interface DoSomething {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    void doSomething();

}
