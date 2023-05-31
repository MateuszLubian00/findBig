import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

public class junitTests {
    @Test
    public void simple(){
        Map expected = new TreeMap<>();
        Map result = FileOp.readFiles("../test/test files");


    }

    @Test
    /* Checks if the output is not null or empty, don't care about order. */
    public void noSubdirSimple() {
        Map result = FileOp.readFiles("/test/test files/dir1/");

        Assert.assertNotEquals(null, result);
        Assert.assertNotEquals(new TreeMap<>(), result);
        System.out.print(result);
    }

    @Test
    /* Pointing to an empty directory should output an empty TreeMap. */
    public void emptyDir() {
        Map result = FileOp.readFiles("/test/test files/dir0");

        Assert.assertEquals(new TreeMap<>(), result);
    }
}