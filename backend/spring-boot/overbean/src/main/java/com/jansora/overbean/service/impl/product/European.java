package com.jansora.overbean.service.impl.product;

import com.jansora.overbean.service.EuropeanService;
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
