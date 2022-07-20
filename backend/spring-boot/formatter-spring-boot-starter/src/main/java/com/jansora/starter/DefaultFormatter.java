package com.jansora.starter;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan <br>
 * @version 1.0 <br>
 * @CreateDate 2020/11/24 11:52:44  <br>
 * @see com.jansora.starter <br>
 * @since 1.0 <br>
 */
public class DefaultFormatter implements Formatter {

    @Override
    public String formatter(Object object) {
        return "com.jansora.starter.FormatterAutoConfiguration: formatter :\n" + String.valueOf(object) + "";
    }
}
