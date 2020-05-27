package org.fenixsoft.jvm.chapter7;

/**
 * <clinit>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块（static{}块）中的
 * 语句合并产生的，编译器收集的顺序是由语句在源文件中出现的【顺序】决定的，静态语句块中只能访问
 * 到定义在静态语句块之前的变量，定义在它之后的变量，在前面的静态语句块可以赋值，但是不能访问
 *
 * @author sumkor
 * @since 2020/5/12
 */
public class ClinitTest {

    static {
        i = 0; // 给变量赋值可以正常编译通过
//        System.out.print(i); // 这句编译器会提示“非法向前引用”
    }
    static int i = 1;

    static {
        System.out.println(i);
    }

    public static void main(String[] args) {
        new ClinitTest();
    }
}
