package com.jansora.demo.spring;

/**
 * 给你一幅由 a × a 矩阵表示的图像，。请你设计一种算法，将图像旋转 90 度。
 */
public class ImageRotate {


    public static int[][] rotate(int[][] image) {
        if (image.length == 0) {
            return image;
        }
        int is = image.length;
        int js = is;
        for (int i = 0; i < is; i++) {
            for (int j = i; j < (js - j); j++) {
                transfer(image, i, j, is - 1);
            }
        }
        return image;
    }


    /**
     * 顺时针旋转单个像素点
     */
    public static void transfer(int[][] image, int i, int j, int is) {
        int a = image[i][j];
        int b = image[i][is - j];
        int c = image[is - i][j];
        int d = image[is - i][is - j];
        image[i][j] = d;
        image[i][is - j] = a;
        image[is - i][j] = b;
        image[is - i][is - j] = c;
    }



    public static void main(String[] args) throws Throwable {
        int[][] image = {{1,2,3}, {4,5,5}, {7,8,9}};
        rotate(image);

    }



}
