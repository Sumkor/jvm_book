package org.fenixsoft.jvm.chapter8;

import org.junit.Test;

import java.util.Random;

/**
 * 方法静态分派演示
 * 在重载时是通过参数的静态类型而不是实际类型作为判定依据
 *
 * @author zzm
 */
public class StaticDispatch {

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);

        sr.sayHello(new Man());
    }


    @Test
    public void test() {
        StaticDispatch sr = new StaticDispatch();

        // 实际类型变化：实际类型变化的结果在运行期才可确定，编译器在编译程序的时候并不知道一个对象的实际类型是什么
        Human human = (new Random()).nextBoolean() ? new Man() : new Woman();

        // 静态类型变化：静态类型的变化仅仅在使用时发生，变量本身的静态类型不会被改变，并且最终的静态类型是在编译期可知的
        sr.sayHello((Man) human);
        sr.sayHello((Woman) human);
    }
}
