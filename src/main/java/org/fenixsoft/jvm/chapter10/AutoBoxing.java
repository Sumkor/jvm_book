package org.fenixsoft.jvm.chapter10;

/**
 * 包装类的“==”运算在不遇到算术运算的情况下不会自动拆箱，
 * 以及它们equals()方法不处理数据转型的关系
 *
 * @author zzm
 */
public class AutoBoxing {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        Integer h = 128;
        Integer i = 128;
        System.out.println(c == d);
        System.out.println(e == f);
        System.out.println(c == (a + b));
        System.out.println(c.equals(a + b));
        System.out.println(g == (a + b));// true
        System.out.println(g.equals(a + b));// false 不处理数据转型的关系
        System.out.println(h == i);// 两个不同的Integer对象
    }
}
