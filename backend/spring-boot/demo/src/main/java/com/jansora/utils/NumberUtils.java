package com.jansora.utils;

import com.jansora.infrastructure.dto.CountDto;

import java.util.List;

/**
 * <Description> NumberUtils <br>
 *
 * @author jansora <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @CreateDate 2022/3/20 20:23 <br>
 * @since 1.0 <br>
 */
public class NumberUtils {

    public static List<CountDto> formatMaxPercent(List<CountDto> reqs) {
        Long max = reqs.stream().map(CountDto::getCount).max(Long::compareTo).orElse(1L);
        reqs.forEach(req -> req.setCount(req.getCount() * 100 / max));
        return reqs;
    }

    public static List<CountDto> sortCountDesc(List<CountDto> reqs) {
        Long max = reqs.stream().map(CountDto::getCount).max(Long::compareTo).orElse(1L);
        reqs.forEach(req -> req.setCount(req.getCount() * 100 / max));
        return reqs;
    }
}
