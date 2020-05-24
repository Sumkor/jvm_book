package org.fenixsoft.jvm.chapter8;

import org.junit.Test;

/**
 * @author Sumkor
 * @since 2020/5/24
 */
public class DynamicTest {

    /**
     * 能够正常编译，但运行的时候会出现NegativeArraySizeException异常(运行时异常)
     * 一门语言的哪一种检查行为要在运行期进行，哪一种检查要在编译期进行并没有什么必然的因果逻辑关系，关键是在语言规范中人为设立的约定。
     */
    @Test(expected  = NegativeArraySizeException.class)
    public void test() {
        int[][][] array = new int[1][0][-1];
    }
}
