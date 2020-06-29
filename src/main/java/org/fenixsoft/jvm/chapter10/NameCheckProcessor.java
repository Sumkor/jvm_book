package org.fenixsoft.jvm.chapter10;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * https://github.com/zack624/JVMUnderstanding/
 * 插入式注解处理器，对源码的命名风格作校验
 * javac -encoding UTF-8 .\NameChecker.java
 * javac -encoding UTF-8 .\NameCheckProcessor.java
 * javac -encoding UTF-8 -processor NameCheckProcessor .\BADLY_NAMED_CODE.java
 *
 * @author Sumkor
 * @since 2020/6/30
 */
@SupportedAnnotationTypes("*")// 可以用"*"表示支持所有Annotations
@SupportedSourceVersion(SourceVersion.RELEASE_8)// 只支持JDK 1.8的Java代码
public class NameCheckProcessor extends AbstractProcessor {

    private NameChecker nChecker;

    /**
     * 初始化名称检查插件
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        nChecker = new NameChecker(processingEnv);
    }

    /**
     * 对输入的语法树的各个节点进行进行名称检查
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!roundEnv.processingOver()){
            for (Element elem: roundEnv.getRootElements()){
                nChecker.checkNames(elem);
            }
        }
        return false;
    }
}
