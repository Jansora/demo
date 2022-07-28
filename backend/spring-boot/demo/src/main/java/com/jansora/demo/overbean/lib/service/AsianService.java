package com.jansora.demo.overbean.lib.service;

public interface AsianService {
    default void sayHello() {
        System.out.println("AsianService: sayHello.");
    }
}
