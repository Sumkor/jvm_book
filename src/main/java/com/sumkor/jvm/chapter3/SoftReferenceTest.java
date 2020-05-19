package com.sumkor.jvm.chapter3;

import org.junit.Test;

import java.lang.ref.SoftReference;

/**
 * 软引用：在系统将要发生内存溢出异常前，会把这些对象列进回收范围之中进行第二次回收，如果这次回收还没有足够的内存，才会抛出内存溢出异常
 *
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * @author sumkor
 * @since 2020/3/28
 */
public class SoftReferenceTest {

    /**
     * JDK1.8 执行结果如下，可见在触发gc时，内存空间充足，并不会回收软引用
     *
     softReference.get() = [B@5d6f64b1
     softReference.get() = [B@5d6f64b1
     [GC (System.gc())  14584K->9644K(19456K), 0.0040375 secs]
     [Full GC (System.gc())  9644K->9508K(19456K), 0.0115994 secs]
     softReference.get() = [B@5d6f64b1
     *
     */
    @Test
    public void test01() {

        byte[] allocation01 = new byte[1024 * 1024 * 8];
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(allocation01);
        // 此时，对于这个byte数组对象，有两个引用路径，一个是来自SoftReference对象的软引用，一个来自变量allocation01的强引用，所以这个数组对象是强可及对象。

        System.out.println("softReference.get() = " + softReference.get());
        allocation01 = null;
        // 结束变量allocation01对这个byte数组实例的强引用，此后该byte数组对象变成一个软可及对象，可以通过softReference进行访问
        System.out.println("softReference.get() = " + softReference.get());

        System.gc();
        System.out.println("softReference.get() = " + softReference.get());
    }

    /**
     * JDK1.8 执行结果如下，可见在触发gc时，内存空间不足，回收软引用
     *
     softReference.get() = [B@5d6f64b1
     softReference.get() = [B@5d6f64b1
     [GC (Allocation Failure)  14749K->9636K(19456K), 0.0056237 secs]
     [GC (Allocation Failure)  9636K->9684K(19456K), 0.0014787 secs]
     [Full GC (Allocation Failure)  9684K->9508K(19456K), 0.0128735 secs]
     [GC (Allocation Failure)  9508K->9508K(19456K), 0.0006353 secs]
     [Full GC (Allocation Failure)  9508K->1261K(19456K), 0.0107362 secs]
     softReference.get() = null
     *
     */
    @Test
    public void test02() {
        byte[] allocation01 = new byte[1024 * 1024 * 8];
        SoftReference<byte[]> softReference = new SoftReference<byte[]>(allocation01);
        // 此时，对于这个byte数组对象，有两个引用路径，一个是来自SoftReference对象的软引用，一个来自变量allocation01的强引用，所以这个数组对象是强可及对象。

        System.out.println("softReference.get() = " + softReference.get());
        allocation01 = null;
        // 结束变量allocation01对这个byte数组实例的强引用，此后该byte数组对象变成一个软可及对象，可以通过softReference进行访问
        System.out.println("softReference.get() = " + softReference.get());

        byte[] allocation02 = new byte[1024 * 1024 * 8];
        System.out.println("softReference.get() = " + softReference.get());
    }
}
