package com.jansora.demo.example.overbean.service;

public interface AsianService {
    default void sayHello() {
        System.out.println("AsianService: sayHello.");
    }
}
