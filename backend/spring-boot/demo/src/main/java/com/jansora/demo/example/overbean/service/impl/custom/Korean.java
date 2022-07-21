package com.jansora.demo.example.overbean.service.impl.custom;

import com.jansora.demo.example.overbean.service.AsianService;
import org.springframework.stereotype.Service;

/**
 * 朝鲜
 */
@Service
public class Korean implements AsianService {
    @Override
    public void sayHello() {
        System.out.println("Korean: sayHello.");
    }
}
