package com.jansora.demo.infrastructure.function;

/**
 * <Description> 支持抛出异常的 lambda function <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/12 PM02:22 <br>
 * @since 1.0 <br>
 */
@FunctionalInterface
public interface DoSomethingWithThrowable {

    /**
     * Applies this function to the given argument.
     *
     * @return the function result
     */
    void doSomething() throws Throwable;

}
