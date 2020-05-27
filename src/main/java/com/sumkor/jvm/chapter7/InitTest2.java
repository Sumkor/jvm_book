package com.sumkor.jvm.chapter7;

/**
 * @author Sumkor
 * @since 2020/5/28
 */
public class InitTest2 {

    public InitTest2() {
        i++;
        System.out.println("构造方法：" + i);// 不会提示“非法向前引用”
    }

    int i = 0;

    {
        i++;
        System.out.println("代码块：" + i);
    }

    public static void main(String[] args) {
        new InitTest2();
        /**
         * 执行结果：
         *
         * 代码块：1
         * 构造方法：2
         */
    }
}
