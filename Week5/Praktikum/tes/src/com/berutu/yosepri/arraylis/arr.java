package com.berutu.yosepri.arraylis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class arr {

    public static void main(String[] args) {
        List arr = new ArrayList<Integer>();
        Scanner scn = new Scanner(System.in);
        for(int i=0; i< 10; i++){
            arr.add(scn.nextInt());
        }
//        arr.sort(Comparator.naturalOrder());
        for(int i=0; i<10; i++){
            System.out.println(arr.get(i));
        }
    }


}
