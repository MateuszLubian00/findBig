import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class junitTests {

    //
    // Simple test cases
    //

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
        AbstractMap.SimpleEntry<String, Long> expected = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir2\\dir3\\2.txt", 2L);

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    /* Checks if the output contains files from subdirectories of subdirectories, don't care about order. */
    public void checkSubdirSubdirSimple() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files", true, false);
        AbstractMap.SimpleEntry<String, Long> expected = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir2\\dir3\\2.txt", 2L);

        Assert.assertTrue(result.contains(expected));
    }

    @Test
    /* Checks if the output contains files with the same name and size (but different locations). */
    public void checkDuplicates() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files", true, true);
        AbstractMap.SimpleEntry<String, Long> first = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir1\\16.txt", 16L);
        AbstractMap.SimpleEntry<String, Long> second = new AbstractMap.SimpleEntry<>("\\test\\test files\\16.txt", 16L);

        Assert.assertTrue(result.contains(first));
        Assert.assertTrue(result.contains(second));
    }

    @Test
    /* Pointing to an empty directory should output an empty TreeSet. */
    public void emptyDir() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir0", false, false);

        Assert.assertEquals(new TreeSet<Map.Entry<String, Long>>(), result);
    }

    //
    // Test cases based on order
    //

    @Test
    /* Check if two test files are added in descending order. */
    public void correctTwoElemOrderDesc() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir1", false, true);
        AbstractMap.SimpleEntry<String, Long> first = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir1\\16.txt", 16L);
        AbstractMap.SimpleEntry<String, Long> second = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir1\\10.txt", 10L);

        Assert.assertEquals(first, result.first());
        Assert.assertEquals(second, result.last());
    }

    /* Check if two test files are added in ascending order. */
    @Test
    public void correctTwoElemOrderAsc() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files\\dir1", false, false);
        AbstractMap.SimpleEntry<String, Long> first = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir1\\10.txt", 10L);
        AbstractMap.SimpleEntry<String, Long> second = new AbstractMap.SimpleEntry<>("\\test\\test files\\dir1\\16.txt", 16L);

        Assert.assertEquals(first, result.first());
        Assert.assertEquals(second, result.last());
    }
    @Test
    /* Check if two files close in size are in correct descending order compared to each other. */
    public void correctCloseSizeOrderDesc() {
        TreeSet<Map.Entry<String, Long>> result = FileOp.readFiles("\\test\\test files", false, true);
        AbstractMap.SimpleEntry<String, Long> first = new AbstractMap.SimpleEntry<>("\\test\\test files\\1025.txt", 1025L);
        AbstractMap.SimpleEntry<String, Long> second = new AbstractMap.SimpleEntry<>("\\test\\test files\\1024.txt", 1024L);

        Assert.assertEquals(first, result.first());
        result.remove(first);
        Assert.assertEquals(second, result.first());
    }
}