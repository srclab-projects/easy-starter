package xyz.srclab.common.bytecode.provider.invoke.asm;

import com.google.common.base.CharMatcher;
import xyz.srclab.common.base.Describer;
import xyz.srclab.common.base.Shares;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author sunqian
 */
public class AsmInvokerHelper {

    private static final String GENERATED_CLASS_ROOT_PACKAGE =
            AsmInvokerHelper.class.getPackage().getName() + ".generated";

    private static final AtomicLong classCounter = new AtomicLong();

    private static final CharMatcher nonJavaNamingMatcher = Shares.NON_JAVA_NAMING_MATCHER;

    public static String generateConstructorInvokerClassName(Constructor<?> constructor, String signature) {
        String constructorDescription = Describer.constructorToString(constructor);
        return GENERATED_CLASS_ROOT_PACKAGE +
                "." +
                nonJavaNamingMatcher.replaceFrom(constructorDescription, "$") +
                "$$CreatedBy$" +
                signature +
                "$$" +
                classCounter.getAndIncrement();
    }

    public static String generateMethodInvokerClassName(Method method, String signature) {
        String methodDescription = Describer.methodToString(method);
        return GENERATED_CLASS_ROOT_PACKAGE +
                "." +
                nonJavaNamingMatcher.replaceFrom(methodDescription, "$") +
                "$$CreatedBy$" +
                signature +
                "$$" +
                classCounter.getAndIncrement();
    }
}