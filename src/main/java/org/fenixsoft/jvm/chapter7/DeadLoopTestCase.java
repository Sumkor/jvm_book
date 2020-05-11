package org.fenixsoft.jvm.chapter7;

/**
 * Java虚拟机必须保证一个类的<clinit>()方法在多线程环境中被正确地加锁同步，如果多个线程同
 * 时去初始化一个类，那么只会有其中一个线程去执行这个类的<clinit>()方法，其他线程都需要阻塞等
 * 待，直到活动线程执行完毕<clinit>()方法。如果在一个类的<clinit>()方法中有耗时很长的操作，那就
 * 可能造成多个进程阻塞，在实际应用中这种阻塞往往是很隐蔽的
 *
 * @author zzm
 */
public class DeadLoopTestCase {

    static class DeadLoopClass {
        static {
            // 如果不加上这个if语句，编译器将提示“Initializer does not complete normally”并拒绝编译
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }

}
