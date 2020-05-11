package org.fenixsoft.jvm.chapter6;

/**
 * 6.3.7
 * 代码清单6-5　异常表运作演示
 *
 * @author sumkor
 * @since 2020/5/10
 */
public class IncTest {

    public int inc() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public static void main(String[] args) {
        int inc = new IncTest().inc();
        System.out.println("inc = " + inc);
    }
}
