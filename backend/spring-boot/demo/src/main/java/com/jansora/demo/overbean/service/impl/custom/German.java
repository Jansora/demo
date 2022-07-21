package com.jansora.demo.overbean.service.impl.custom;

import com.jansora.demo.overbean.service.EuropeanService;
import org.springframework.stereotype.Service;

/**
 * 德国
 */
@Service
public class German implements EuropeanService {

    @Override
    public void sayHello() {
        System.out.println("German: sayHello.");
    }
}
