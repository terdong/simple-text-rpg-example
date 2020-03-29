package com.teamgehem.simple_rpg;

/**
 * Created by DongHee Kim on 2018-01-22 022.
 */
public class ExpTest {
    public static void main(String[] args) {


        for(int i=0; i< 100; ++i){
            System.out.println((i * i) * 2 + 100);
        }

        System.out.println("level = " + (int)Math.sqrt(19600/2 - 100/2));

    }
}
