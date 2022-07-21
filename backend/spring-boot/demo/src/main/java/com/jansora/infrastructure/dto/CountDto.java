package com.jansora.infrastructure.dto;

import java.io.Serializable;

/**
 * <Description> Description for CountDto <br>
 *
 * @author jansora <br>
 * @version 1.0 <br>
 * @github https://github.com/Jansora
 * @CreateDate 2021/5/4 11:46:43 <br>
 * @see com.jansora.app.params.resp <br>
 * @since 1.0 <br>
 */
public class CountDto implements Serializable {

    private String name;

    private Long count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountDto{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
