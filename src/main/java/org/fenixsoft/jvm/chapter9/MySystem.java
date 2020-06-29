package org.fenixsoft.jvm.chapter9;

import java.util.Random;

/**
 * @author Sumkor
 * @since 2020/6/29
 */
public class MySystem {

    public static void main(String[] args) {
        String str = (new Random()).nextBoolean() ? "aaa" : "bbb";
        System.out.println(str);
    }
}
