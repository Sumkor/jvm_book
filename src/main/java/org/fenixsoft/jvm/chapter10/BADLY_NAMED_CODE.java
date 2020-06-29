package org.fenixsoft.jvm.chapter10;

/**
 * @author Sumkor
 * @since 2020/6/30
 */
public class BADLY_NAMED_CODE {

    enum colors{
        red, blue, green;
    }

    static final int _FORTY_TWO = 42;

    public static int NOT_A_CONSTANT = _FORTY_TWO;

    protected void BADLY_NAMED_CODE(){
        return;
    }

    public void NOTCamelCASEmethodName(){
        return;
    }

    /***************  运行javac -processor的执行结果，这个地方需要把这几个类copy到项目外同一路径下，去掉package信息再执行
     PS C:\TOOL\Code\test> javac -encoding UTF-8 .\NameChecker.java
     PS C:\TOOL\Code\test> javac -encoding UTF-8 .\NameCheckProcessor.java
     PS C:\TOOL\Code\test> javac -encoding UTF-8 -processor NameCheckProcessor .\BADLY_NAMED_CODE.java
     .\BADLY_NAMED_CODE.java:7: 警告: 名称“BADLY_NAMED_CODE”应当符合驼式命名法（Camel Case Names）
     public class BADLY_NAMED_CODE {
     ^
     .\BADLY_NAMED_CODE.java:9: 警告: 名称“colors”应当以大写字母开头
     enum colors{
     ^
     .\BADLY_NAMED_CODE.java:10: 警告: 常量“red”应当全部以大写字母或下划线命名，并且以字母开头
     red, blue, green;
     ^
     .\BADLY_NAMED_CODE.java:10: 警告: 常量“blue”应当全部以大写字母或下划线命名，并且以字母开头
     red, blue, green;
     ^
     .\BADLY_NAMED_CODE.java:10: 警告: 常量“green”应当全部以大写字母或下划线命名，并且以字母开头
     red, blue, green;
     ^
     .\BADLY_NAMED_CODE.java:13: 警告: 常量“_FORTY_TWO”应当全部以大写字母或下划线命名，并且以字母开头
     static final int _FORTY_TWO = 42;
     ^
     .\BADLY_NAMED_CODE.java:15: 警告: 名称“NOT_A_CONSTANT”应当以小写字母开头
     public static int NOT_A_CONSTANT = _FORTY_TWO;
     ^
     .\BADLY_NAMED_CODE.java:17: 警告: 一个普通方法 “BADLY_NAMED_CODE”不应当与类名重复，避免与构造函数产生混淆
     protected void BADLY_NAMED_CODE(){
     ^
     .\BADLY_NAMED_CODE.java:17: 警告: 名称“BADLY_NAMED_CODE”应当以小写字母开头
     protected void BADLY_NAMED_CODE(){
     ^
     .\BADLY_NAMED_CODE.java:21: 警告: 名称“NOTCamelCASEmethodName”应当以小写字母开头
     public void NOTCamelCASEmethodName(){
     ^
     10 个警告
     *************/
}
