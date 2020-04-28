package com.sumkor.chapter3;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

/**
 * 虚引用：一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来取得一个对象实例。
 * 为一个对象设置虚引用关联的唯一目的只是为了能在这个对象被收集器回收时收到一个系统通知。
 * <p>
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
 *
 * @author sumkor
 * @since 2020/3/30
 */
public class PhantomReferenceTest {

    /**
     * phantom总是为null
     */
    @Test
    public void test01() {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        byte[] allocation01 = new byte[1024 * 1024 * 8];
        PhantomReference<byte[]> phantom = new PhantomReference<>(allocation01, referenceQueue);

        Reference<?> poll = referenceQueue.poll();
        System.out.println("poll = " + poll);// null
        System.out.println("phantom.get() = " + phantom.get());// null
    }

    /**
     * phantom总是为null，
     * 当byte数组对象被垃圾回收器回收时，垃圾收集器会把要回收的对象添加到引用队列ReferenceQueue，得到一个通知
     * <p>
     * poll = null
     * phantom.get() = null
     * [GC (System.gc())  14877K->9625K(19456K), 0.0054123 secs]
     * [Full GC (System.gc())  9625K->9515K(19456K), 0.0205644 secs]
     * poll = java.lang.ref.PhantomReference@5e8c92f4
     * phantom.get() = null
     */
    @Test
    public void test02() throws InterruptedException {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        byte[] allocation01 = new byte[1024 * 1024 * 8];
        PhantomReference<byte[]> phantom = new PhantomReference<>(allocation01, referenceQueue);
        allocation01 = null;

        Reference<?> poll = referenceQueue.poll();
        System.out.println("poll = " + poll);// null
        System.out.println("phantom.get() = " + phantom.get());// null

        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);

        poll = referenceQueue.poll();
        System.out.println("poll = " + poll);// java.lang.ref.PhantomReference@5d6f64b1
        System.out.println("phantom.get() = " + phantom.get());// null
    }

    /**
     * 当垃圾收集器对加入队列的对象改变可触及性的时候，就可以收到异步通知了。
     * http://www.importnew.com/20992.html
     */
    @Test
    public void test03() throws InterruptedException {
        String abc = new String("abc");
        System.out.println(abc.getClass() + "@" + abc.hashCode());
        final ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        new Thread() {
            public void run() {
                while (true) {
                    Object obj = referenceQueue.poll();
                    if (obj != null) {
                        try {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            Object result = rereferent.get(obj);
                            System.out.println("gc will collect："
                                    + result.getClass() + "@"
                                    + result.hashCode() + "\t"
                                    + (String) result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
        PhantomReference<String> abcWeakRef = new PhantomReference<String>(abc, referenceQueue);
        abc = null;
        Thread.currentThread().sleep(3000);
        System.gc();
        Thread.currentThread().sleep(3000);
    }
}
