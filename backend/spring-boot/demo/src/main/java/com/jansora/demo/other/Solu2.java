package com.jansora.demo.other;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 给你一幅由 a × a 矩阵表示的图像，。请你设计一种算法，将图像旋转 90 度。
 */
public class Solu2 {




    public static void main(String[] args) throws Throwable {



        Thread a = new Thread(() -> System.out.println("a"));

        Thread b = new Thread(() -> {
            try {
                a.join();
                System.out.println("b");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread c = new Thread(() -> {
            try {
                b.join();
                System.out.println("c");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        for (int i = 0;  i< 10; i++ ) {
            a.start();
            b.start();
            c.start();
        }

        ReentrantLock lock = new ReentrantLock();
        lock.newCondition();
    }



}
