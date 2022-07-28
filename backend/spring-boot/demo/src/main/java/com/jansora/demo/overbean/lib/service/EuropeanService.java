package com.jansora.demo.overbean.lib.service;

public interface EuropeanService {
    default void sayHello() {
        System.out.println("EuropeanService: sayHello.");
    }
}
