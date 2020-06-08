package com.sumkor.jvm.chapter10;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Sumkor
 * @since 2020/6/1
 */
public class GenericsTest {

    public <T> List<T> execute(T t) {
        return null;
    }

    private List<String> list = Arrays.asList("a", "b");

    /**
     * Java中的数组是支持协变（Covariant）的
     */
    @Test
    public void test01() {
        Object[] array = new String[10];
        array[0] = 10; // 编译期不会有问题，运行时会报错：java.lang.ArrayStoreException

        ArrayList things = new ArrayList();
        things.add(Integer.valueOf(10)); //编译、运行时都不会报错
        things.add("hello world");
        System.out.println("things = " + things);
    }

    /**
     * 所谓的擦除，仅仅是对方法的Code属性中的字节码进行擦除，
     * 实际上元数据中还是保留了泛型信息，这也是我们在编码时能通过反射手段取得参数化类型的根本依据。
     */
    @Test
    public void test02() throws Exception {
        Field list = GenericsTest.class.getDeclaredField("list");
        list.setAccessible(true);
        Type genericType = list.getGenericType();
        System.out.println("genericType = " + genericType);

        ParameterizedType parameterizedType = (ParameterizedType) genericType;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println("actualTypeArguments = " + actualTypeArguments[0]);
        // actualTypeArguments = class java.lang.String

        if (String.class == actualTypeArguments[0]) {
            System.out.println("true");
        }
    }
}
