package com.jansora.infrastructure.exception.system;

import com.jansora.infrastructure.exception.BaseAppException;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 * 2020/12/02 15:47:59
 */
public class CommandException extends BaseAppException {

    public CommandException() {
        super("500", "服务器运行命令时出现异常");
    }

    public CommandException(String errorDesc) {
        super("500", errorDesc);
    }
}
