import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class junitTests {
    @Test
    /* Checks if the output is not null or empty, don't care about order. */
    public void noSubdirSimple() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir1", false, false);

        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(new TreeSet<Map.Entry<String, Long>>(), result);
    }

    @Test
    /* Checks if the output contains files from subdirectories, don't care about order. */
    public void checkSubdirSimple() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir2", true, false);
        AbstractMap.SimpleEntry<String, Long> expected = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir2\\dir3\\2.txt", (Long.valueOf(2)));
        Assert.assertTrue(result.contains(expected));
    }

    @Test
    /* Checks if the output contains files from subdirectories of subdirectories, don't care about order. */
    public void checkSubdirSubdirSimple() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files", true, false);
        AbstractMap.SimpleEntry<String, Long> expected = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir2\\dir3\\2.txt", (Long.valueOf(2)));

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    /* Pointing to an empty directory should output an empty TreeMap. */
    public void emptyDir() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir0", false, false);

        Assert.assertEquals(new TreeSet<Map.Entry<String, Long>>(), result);
    }
}