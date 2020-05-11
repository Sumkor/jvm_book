package org.fenixsoft.jvm.chapter7;

/**
 * 由于父类的<clinit>()方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值操作
 *
 * @author sumkor
 * @since 2020/5/12
 */
public class ClinitTest2 {

    static class Parent {
        public static int A = 1;
        static {
            A = 2;
        }
    }
    static class Sub extends Parent {
        public static int B = A;
    }

    public static void main(String[] args) {
        System.out.println(Sub.B);
    }
}
