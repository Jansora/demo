package com.jansora.demo.spring.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <Description> <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @date 2022/8/25 AM11:58 <br>
 * @since 1.0 <br>
 */
@Component
public class BeanA {
    @Autowired
    BeanB beanB;

}
