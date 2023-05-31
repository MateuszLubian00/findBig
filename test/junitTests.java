import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class junitTests {
    @Test
    /* Checks if the output is not null or empty, don't care about order. */
    public void noSubdirSimple() {
        Map result = FileOp.readFiles("/test/test files/dir1", false);

        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(new TreeMap<>(), result);
    }

    @Test
    /* Checks if the output contains files from subdirectories, don't care about order. */
    public void checkSubdirSimple() {
        Map result = FileOp.readFiles("/test/test files/dir2", true);

        Assert.assertTrue(result.containsValue(Long.valueOf(2)));
    }

    @Test
    /* Checks if the output contains files from subdirectories of subdirectories, don't care about order. */
    public void checkSubdirSubdirSimple() {
        Map result = FileOp.readFiles("/test/test files", true);

        Assert.assertTrue(result.containsValue(Long.valueOf(2)));
    }

    @Test
    /* Pointing to an empty directory should output an empty TreeMap. */
    public void emptyDir() {
        Map result = FileOp.readFiles("/test/test files/dir0", false);

        Assert.assertEquals(new TreeMap<>(), result);
    }
}