package com.sumkor.jvm.chapter8;

import java.lang.reflect.Method;

/**
 * 反射也是调用 invokevirtual
 *
 * @author Sumkor
 * @since 2020/7/4
 */
public class InvokeTest {

    public void myMethod() {
        System.out.println("haha");
    }

    public static void main(String[] args) throws Exception {
        InvokeTest invokeTest = new InvokeTest();
        Class<?> aClass = Class.forName("com.sumkor.jvm.chapter8.InvokeTest");
        Method myMethod = aClass.getDeclaredMethod("myMethod");
        myMethod.invoke(invokeTest);
    }

    /**
     *
     * javap -c .\InvokeTest.class
     *
     * Compiled from "InvokeTest.java"
     * public class com.sumkor.jvm.chapter8.InvokeTest {
     *   public com.sumkor.jvm.chapter8.InvokeTest();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public void myMethod();
     *     Code:
     *        0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *        3: ldc           #3                  // String haha
     *        5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *        8: return
     *
     *   public static void main(java.lang.String[]) throws java.lang.Exception;
     *     Code:
     *        0: new           #5                  // class com/sumkor/jvm/chapter8/InvokeTest
     *        3: dup
     *        4: invokespecial #6                  // Method "<init>":()V
     *        7: astore_1
     *        8: ldc           #7                  // String com.sumkor.jvm.chapter8.InvokeTest
     *       10: invokestatic  #8                  // Method java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
     *       13: astore_2
     *       14: aload_2
     *       15: ldc           #9                  // String myMethod
     *       17: iconst_0
     *       18: anewarray     #10                 // class java/lang/Class
     *       21: invokevirtual #11                 // Method java/lang/Class.getDeclaredMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
     *       24: astore_3
     *       25: aload_3
     *       26: aload_1
     *       27: iconst_0
     *       28: anewarray     #12                 // class java/lang/Object
     *       31: invokevirtual #13                 // Method java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
     *       34: pop
     *       35: return
     * }
     *
     *
     */
}
