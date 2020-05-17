package org.fenixsoft.jvm.chapter8;

/**
 * placeholder能否被回收的根本原因就是：局部变量表中的变量槽是否还存有关于placeholder数组对象的引用。
 * 第一次修改中，代码虽然已经离开了placeholder的作用域，但在此之后，再没有发生过任何对局部变量表的读写操作，
 * placeholder原本所占用的变量槽还没有被其他变量所复用，所以作为GC Roots一部分的局部变量表仍然保持着对它的关联。
 * <p>
 * -verbose:gc
 *
 * @author sumkor
 * @since 2020/5/17
 */
public class SlotTest {

    /**
     * 在实际情况中，即时编译才是虚拟机执行代码的主要方式，赋null值的操作在经过即时编译优化后几乎是
     * 一定会被当作无效操作消除掉的，这时候将变量设置为null就是毫无意义的行为。
     */
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
//            placeholder = null;// 清除变量槽对 placeholder 的引用，使得 placeholder 被回收
        }

//        int a = 0;// 变量 a 复用 placeholder 的变量槽，使得 placeholder 被回收
        System.gc();

    }
}
