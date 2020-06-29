package org.fenixsoft.jvm.chapter9;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Sumkor
 * @since 2020/6/29
 */
public class JavaclassExecuterTest {

    public static void main(String[] args) throws IOException {
        String classFilePath = "C:\\TOOL\\Code\\jvm_book\\target\\classes\\org\\fenixsoft\\jvm\\chapter9\\MySystem.class";
        FileInputStream fileInputStream = new FileInputStream(classFilePath);
        byte[] bytes = new byte[fileInputStream.available()];
        String execute = JavaclassExecuter.execute(bytes);
        System.out.println("execute = " + execute);
    }
}
