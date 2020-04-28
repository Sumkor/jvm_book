package com.sumkor.chapter3;

/**
 * 强引用：无论任何情况下，只要强引用关系还存在，垃圾收集器就永远不会回收掉被引用的对象。
 *
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * 堆大小为20m，其中新生代大小为10m，按照1:8比例分配，Eden区大小设置为8m
 * 此时存入8m大小的变量，直接存入老年代（tenured generation）
 *
 * @author sumkor
 * @since 2020/3/28
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        // 10m大小，内存溢出
        byte[] allocation01 = new byte[1024 * 1024 * 9];
        byte[] allocation02 = new byte[1024 * 1024 * 9];
    }
    /**
     * JDK1.8
     *
     [GC (Allocation Failure)  11197K->10032K(19456K), 0.0014301 secs]
     [Full GC (Ergonomics)  10032K->9851K(19456K), 0.0072375 secs]
     [GC (Allocation Failure)  9851K->9851K(19456K), 0.0004413 secs]
     [Full GC (Allocation Failure)  9851K->9833K(19456K), 0.0093839 secs]
     Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     at com.sumkor.reference.StrongReferenceTest.main(StrongReferenceTest.java:18)
     *
     */
}
