package com.jansora.demo.infrastructure.function.utils;

import com.jansora.demo.infrastructure.exception.transform.FormatException;
import com.jansora.demo.infrastructure.exception.web.InvalidArgumentException;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author zhang.yangyuan (jansora)
 * 2020/12/03 22:36:38
 */
@Configuration
public class Utils {

    /*
    Bean Copy
     */
    public static <T, F> List<T> copy(Class<T> tClass, Class<F> sClass, List<F> sources) throws FormatException {

        try {
            List<T> arrs = new ArrayList<>();

            for (F source : sources) {
                T target = tClass.newInstance();
                BeanUtils.copyProperties(source, target);
                arrs.add(target);
            }

            return arrs;

        } catch (InstantiationException | IllegalAccessException e) {
            throw new FormatException(e.getLocalizedMessage());
        }

    }

    /*
    Bean Copy
     */
    public static <T> T copy(Class<T> tClass, Object... sources) throws FormatException, InvalidArgumentException {
        AssertUtils.nonNull(tClass, sources);
        try {
            T target = tClass.newInstance();
            for (Object source : sources) {
                BeanUtils.copyProperties(source, target);
            }
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FormatException(e.getLocalizedMessage());
        }

    }

    /*
    Bean Copy
     */
    public static <T> T BeanCopyIgnoreNull(Class<T> tClass, Object... sources) throws FormatException {

        try {
            T target = tClass.newInstance();
            for (Object source : sources) {
                if (!ObjectUtils.isEmpty(source)) {
                    BeanUtils.copyProperties(source, target);
                }
            }
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new FormatException(e.getLocalizedMessage());
        }

    }


}
