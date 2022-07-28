package com.jansora.demo.overbean.lib.service.impl.product;

import com.jansora.demo.overbean.lib.service.EuropeanService;
import org.springframework.stereotype.Service;

/**
 * 欧洲人
 */
@Service
public class European implements EuropeanService {

    @Override
    public void sayHello() {
        System.out.println("European: sayHello.");
    }


}
