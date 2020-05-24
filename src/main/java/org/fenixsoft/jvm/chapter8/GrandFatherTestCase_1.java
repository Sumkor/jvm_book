package org.fenixsoft.jvm.chapter8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @author zzm
 */
public class GrandFatherTestCase_1 {

    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        void thinking() {
            System.out.println("i am father");
        }
    }

    /**
     * 必须保证findSpecial()查找方法版本时受到的访问约束（譬如对访问控制的限制、对参数类型的限制）应与使用
     * invokespecial指令一样，两者必须保持精确对等，包括在上面的场景中它只能访问到其直接父类中的方法版本。
     */
    class Son extends Father {
        void thinking() {
            // super.thinking();
            try {
                MethodType mt = MethodType.methodType(void.class);// 方法返回值为void，没有入参
                MethodHandle mh = lookup().findSpecial(GrandFather.class,
                        "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
            }
        }
    }

    public static void main(String[] args) {
        (new GrandFatherTestCase_1().new Son()).thinking();
    }

}
