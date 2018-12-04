/**
 * TestSecond.java
 * Copyright 2018 天津亿网通达网络技术有限公司.
 * All rights reserved.
 * Created on 2018-10-31 15:42
 */
package com.test;

import com.mchange.lang.ShortUtils;
import com.pojo.UserInfo;

import java.util.*;


/**
 * @author 梁家鹄
 * @version 1.0.0, 2018-10-31 15:42
 * @Description
 **/
public class TestSecond
{

    public static void main(String[] args){
/*        String s1 = "hello";
        String s2  = new String("hello");
        String s3 = "he"+"llo";
        String s4 = "he";
        String s5 = "llo";
        String s6 = s4+s5;
        System.out.println(s1==s3);*/

        //简单选择排序
/*        int[] arr={1,3,2,45,65,33,12};
        for(int i = 0;i<arr.length-1;i++){
             int min = i ;
             int temp;
            for(int j = i + 1 ;j<arr.length;j++){
                if(arr[j]<arr[min]){
                    min = j;//获取最小的数
                }

            }
            //把最小的数往前放
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

        for(int a :arr){
            System.out.println(a);
        }*/

    /* //冒泡排序
        int[] arr={1,3,2,45,65,33,12};
        for(int i  = 0 ;i<arr.length-1;i++){
            for(int j  = 0 ;j< arr.length - i -1;j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }

        }`
        for(int a : arr){
            System.out.println(a);
        }*/
    //插入排序
       /*  int[] arr={1,3,2,45,65,33,12};

          for(int i = 1 ;i<arr.length ;i++) {

              int pre = i - 1;
              int current = arr[i];
              while (pre>=0&&arr[pre]>current){
                  arr[pre+1] = arr[pre];
                  pre--;
              }
              arr[pre+1] = current;
          }

        for(int a : arr){
            System.out.println(a);
        }
*/

        int[] arr={1,3,2,45,65,33,12,12,10,99,88,52,63,70,4,7};
       /* int len = arr.length,
                temp,
                gap = 1;
        System.out.println(len);
        while (gap < len / 3) {          // 动态定义间隔序列
            gap = gap * 3 + 1;
        }
        System.out.println("gap:"+gap);
        for (; gap > 0; gap =gap/3) {
            System.out.println("garp:"+gap);
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int pre = i-gap;
                for (; pre> 0 && arr[pre]> temp; pre-=gap) {
                    arr[pre + gap] = arr[pre];
                }
                System.out.println("pre"+pre);
                arr[pre + gap] = temp;
            }
        }*/


/*
     //希尔排序
      int len = arr.length;
        int gap = len/3;
        System.out.println(gap);
     for(;gap>0;gap/=3){
         for(int i=gap;i<len;i++){
             int temp =  arr[i];
             int pre = i - gap;
             while (pre>0&&arr[pre] >temp){
                 arr[pre +gap] = arr[pre];
                 pre -=gap;
             }
             arr[pre+gap] = temp;
         }

     }
        for(int a : arr){
            System.out.println(a);
        }

*/














}}
