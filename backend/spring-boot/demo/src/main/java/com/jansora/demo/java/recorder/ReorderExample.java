package com.jansora.demo.java.recorder;

/**
 * <Description> <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @date 2022/8/25 PM06:51 <br>
 * @since 1.0 <br>
 */
public class ReorderExample {

    int a = 0;
    boolean flag = false;

    public void writer() {
        a = 1; //1 OBJE
        flag = true; //2
    }

    public void reader() {
        if (flag) { //3
            int i = a * a; //4
            System.out.println(i);
        }
    }

}
