/* This is the engine of the whole program, where most of the work will be done.
*
*
*/
import java.util.Map;
public class Logic {

    /* Creates one map of files. */
    public static Map<String, Long> listFiles(boolean recursion) {
        Map<String, Long> filesMap;
        filesMap = FileOp.readFiles("", recursion);


        return filesMap;
    }
}
