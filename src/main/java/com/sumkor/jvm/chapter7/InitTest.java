package com.sumkor.jvm.chapter7;

/**
 * @author Sumkor
 * @since 2020/5/28
 */
public class InitTest {

    {
        i = 0; // 给变量赋值可以正常编译通过
//        System.out.print(i); // 这句编译器会提示“非法向前引用”
    }
    int i = 1;

    {
        System.out.println(i);
    }

    public static void main(String[] args) {
        new InitTest();
    }
}
