package org.fenixsoft.jvm.chapter7;

/**
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 **/
public class NotInitialization_2 {

    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];

        // 用户可直接使用的只有被修饰为public的length属性和clone()方法
        int length = sca.length;
        sca.clone();
    }

}
