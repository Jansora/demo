package com.jansora.demo.infrastructure.exception.web;

import com.jansora.demo.infrastructure.exception.BaseAppException;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 * 2020/12/02 15:47:59
 */
public class BadRequestException extends BaseAppException {

    public BadRequestException() {
        super("400", "请求错误");
    }

    public BadRequestException(String errorDesc) {
        super("400", errorDesc);
    }
}
