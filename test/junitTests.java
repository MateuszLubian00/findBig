import org.junit.Assert;
import org.junit.Test;

public class junitTests {
    @Test
    public void simple(){
        boolean kek = true;
        System.out.printf("nothing");
        Assert.assertTrue(kek);
    }
}