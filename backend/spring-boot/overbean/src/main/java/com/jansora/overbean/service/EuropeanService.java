package com.jansora.overbean.service;

public interface EuropeanService {
    default void sayHello() {
        System.out.println("EuropeanService: sayHello.");
    }
}
