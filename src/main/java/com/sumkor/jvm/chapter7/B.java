package com.sumkor.jvm.chapter7;

/**
 * 结论：
 * 静态变量是全局变量
 * 静态方法在类加载时被执行，只会被执行一次
 * 静态变量和静态方法按照声明的顺序依次执行
 * 父类的静态方法>子类的静态方法>Main函数>父类的代码块>父类的构造方法>子类的代码块>子类的构造方法
 * https://www.cnblogs.com/my12/p/9858848.html
 *
 * @author Sumkor
 * @since 2020/5/28
 */
class A {
    static int i = 0;

    static {
        System.out.println("静态代码块A");
    }

    public A() {
        System.out.println("构造方法A");
    }

    {
        System.out.println("代码块A");
    }

    static {
        i++;
        System.out.println("在类A中静态变量i=" + i);
    }
}

public class B extends A {


    static {
        System.out.println("静态代码块B");
    }

    public B() {
        System.out.println("构造方法B");
    }

    {
        System.out.println("代码块B");
    }

    public static void main(String[] args) {
        System.out.println("--------------------main方法");
        new B();
        System.out.println("--------------------");
        new B();
        /**
         * 执行结果：
         *
         * 静态代码块A
         * 在类A中静态变量i=1
         * 静态代码块B
         * 在类B中静态变量i=2
         * --------------------main方法
         * 代码块A
         * 构造方法A
         * 代码块B
         * 构造方法B
         * --------------------
         * 代码块A
         * 构造方法A
         * 代码块B
         * 构造方法B
         */
    }

    static {
        i++;
        System.out.println("在类B中静态变量i=" + i);
    }

}
