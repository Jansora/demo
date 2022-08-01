package com.jansora.pulsar.dto;

import com.jansora.app.repo.core.carrier.dto.BaseDto;

/**
 * <Description> Description for Message <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/8/1 PM12:13 <br>
 * @since 1.0 <br>
 */
public class Message extends BaseDto  {

    String data;

    public Message() {
    }

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
