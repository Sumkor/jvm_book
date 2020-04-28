package com.sumkor.chapter3;

import java.lang.ref.WeakReference;

/**
 * 弱引用：当垃圾收集器开始工作，无论当前内存是否足够，都会回收掉只被弱引用关联的对象
 *
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * @author sumkor
 * @since 2020/3/30
 */
public class WeakReferenceTest {

    public static void main(String[] args) {

        byte[] allocation01 = new byte[1024 * 1024 * 8];
        WeakReference<byte[]> weakReference = new WeakReference<byte[]>(allocation01);

        System.out.println("weakReference.get() = " + weakReference.get());// [B@154ebadd
        allocation01 = null;
        System.out.println("weakReference.get() = " + weakReference.get());// [B@154ebadd

        System.gc();
        System.out.println("weakReference.get() = " + weakReference.get());// null
    }

    /**
     * JDK1.8
     *
     weakReference.get() = [B@14ae5a5
     weakReference.get() = [B@14ae5a5
     [GC (System.gc())  10177K->9008K(19456K), 0.0011390 secs]
     [Full GC (System.gc())  9008K->643K(19456K), 0.0069800 secs]
     weakReference.get() = null
     */
}
