package org.fenixsoft.jvm.chapter7;

/**
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 **/
public class NotInitialization_2 {

    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];

        /**
         * 这段代码里面触发了另一个名为“[Lorg.fenixsoft.classloading.SuperClass”的类的初始化阶段，对于用户代码来说，这并不是
         * 一个合法的类型名称，它是一个由虚拟机自动生成的、直接继承于java.lang.Object的子类，创建动作由字节码指令newarray触发。
         * 用户可直接使用的只有被修饰为public的length属性和clone()方法
         */
        int length = sca.length;
        sca.clone();
    }

}
