package com.jansora.demo.overbean.service;

public interface AsianService {
    default void sayHello() {
        System.out.println("AsianService: sayHello.");
    }
}
