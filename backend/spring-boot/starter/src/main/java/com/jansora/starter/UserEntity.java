package com.jansora.starter;

import lombok.Data;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 * 2020/11/30 12:27:41
 */
@Data
public class UserEntity {
    String id;
    String name;

    public UserEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
