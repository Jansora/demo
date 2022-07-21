package com.jansora.demo.example.overbean.service.impl.custom;

import com.jansora.demo.example.overbean.service.AsianService;
import org.springframework.stereotype.Service;

/**
 * 中国
 */
@Service
public class Chinese implements AsianService {

    @Override
    public void sayHello() {
        System.out.println("Chinese: sayHello.");
    }
}
