package test.java.xyz.srclab.common.base;

import org.testng.Assert;
import org.testng.annotations.Test;
import xyz.srclab.common.base.Equal;
import xyz.srclab.common.base.Hash;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author sunqian
 */
public class EqualHashTest {

    @Test
    public void testEqual() {
        Assert.assertTrue(Equal.equals("", ""));
        Assert.assertFalse(Equal.equals(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        Assert.assertTrue(Equal.anyOrArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        Assert.assertTrue(Equal.anyOrArrayDeepEquals(
                new Object[]{1, new int[]{1, 2, 3}, 3}, new Object[]{1, new int[]{1, 2, 3}, 3}));
    }

    @Test
    public void testHash() {
        int[] iArray = new int[]{1, 2, 3};
        Assert.assertEquals(Arrays.hashCode(iArray), Hash.anyOrArrayHash(iArray));
        Object[] oArray = new Object[]{1, new int[]{1, 2, 3}, 3};
        Assert.assertEquals(Arrays.deepHashCode(oArray), Hash.anyOrArrayDeepHash(oArray));
        Assert.assertEquals(Objects.hash(1, 2, iArray), Hash.hash(1, 2, iArray));
    }
}
