package com.jansora.demo.infrastructure.exception.web;

import com.jansora.demo.infrastructure.exception.BaseAppException;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 * 2020/12/02 15:47:59
 */
public class InvalidArgumentException extends BaseAppException {

    public InvalidArgumentException() {
        super("406", "参数有误");
    }

    public InvalidArgumentException(String errorDesc) {
        super("406", errorDesc);
    }
}
