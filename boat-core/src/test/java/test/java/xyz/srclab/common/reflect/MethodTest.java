package test.java.xyz.srclab.common.reflect;

import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.srclab.common.collect.Collects;
import xyz.srclab.common.reflect.Reflects;
import xyz.srclab.common.test.TestLogger;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author sunqian
 */
public class MethodTest {

    private static final TestLogger logger = TestLogger.DEFAULT;

    Method superPublicMethod = SuperNewClass.class.getDeclaredMethod("superPublicMethod");
    Method superProtectedMethod = SuperNewClass.class.getDeclaredMethod("superProtectedMethod");
    Method superPrivateMethod = SuperNewClass.class.getDeclaredMethod("superPrivateMethod");
    Method superPackageMethod = SuperNewClass.class.getDeclaredMethod("superPackageMethod");
    Method publicMethod = NewClass.class.getDeclaredMethod("publicMethod");
    Method protectedMethod = NewClass.class.getDeclaredMethod("protectedMethod");
    Method privateMethod = NewClass.class.getDeclaredMethod("privateMethod");
    Method packageMethod = NewClass.class.getDeclaredMethod("packageMethod");
    Method subPublicMethod = SubNewClass.class.getDeclaredMethod("subPublicMethod");
    Method subProtectedMethod = SubNewClass.class.getDeclaredMethod("subProtectedMethod");
    Method subPrivateMethod = SubNewClass.class.getDeclaredMethod("subPrivateMethod");
    Method subPackageMethod = SubNewClass.class.getDeclaredMethod("subPackageMethod");

    public MethodTest() throws NoSuchMethodException {
    }

    @Test
    public void testFind() throws Exception {
        Assert.assertEquals(
                Reflects.methods(NewClass.class),
                Arrays.asList(NewClass.class.getMethods())
        );
        Assert.assertEquals(
                Reflects.declaredMethods(NewClass.class),
                Arrays.asList(NewClass.class.getDeclaredMethods())
        );
        Assert.assertEquals(
                Collects.sorted(Reflects.ownedMethods(SubNewClass.class), Comparator.comparing(Method::toString)),
                Collects.sorted(Arrays.asList(
                        subPublicMethod,
                        publicMethod,
                        NewClass.class.getMethod("equals", Object.class),
                        NewClass.class.getMethod("hashCode"),
                        NewClass.class.getMethod("wait"),
                        NewClass.class.getMethod("wait", long.class),
                        NewClass.class.getMethod("wait", long.class, int.class),
                        NewClass.class.getMethod("toString"),
                        NewClass.class.getMethod("getClass"),
                        NewClass.class.getMethod("notify"),
                        NewClass.class.getMethod("notifyAll"),
                        superPublicMethod,
                        subProtectedMethod,
                        subPrivateMethod,
                        subPackageMethod
                ), Comparator.comparing(Method::toString))
        );

        Assert.assertEquals(
                Reflects.ownedMethodOrNull(NewClass.class, "protectedMethod"),
                NewClass.class.getDeclaredMethod("protectedMethod")
        );
        Assert.assertNull(Reflects.ownedMethodOrNull(NewClass.class, "superProtectedMethod"));

        Assert.assertEquals(
                Reflects.searchMethods(SubNewClass.class, m -> m.getName().contains("ackage")),
                Arrays.asList(subPackageMethod, packageMethod, superPackageMethod)
        );
    }

    @Test
    public void testInvoke() {
        NewClass newClass = new NewClass();
        Assert.assertEquals(
                Reflects.invoke(superPublicMethod, newClass),
                "superPublicField"
        );
        Assert.assertEquals(
                Reflects.invokeForcible(privateMethod, newClass),
                "privateField"
        );
        Assert.expectThrows(IllegalAccessException.class, () ->
                Reflects.invoke(superPrivateMethod, newClass)
        );
        Assert.assertEquals(
                Reflects.invokeForcible(superPrivateMethod, newClass),
                "superPrivateField"
        );
    }
}
