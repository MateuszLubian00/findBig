/* This is the engine of the whole program, where most of the work will be done.
*
*
*/
import java.util.*;

public class Logic {

    /* Creates one map of files. */
    public static TreeSet<Map.Entry<String, Long>> listFiles(boolean recursion, boolean descOrder) {
        TreeSet<Map.Entry<String, Long>> filesMap;
        filesMap = FileOp.readFiles("/test/test files", recursion, descOrder);

        System.out.print(filesMap);
        return filesMap;
    }

    /* Print out the list of files. */
    private static void printFiles(TreeSet<Map.Entry<String, Long>> filesMap, boolean descOrder) {
        System.out.println(filesMap);
    }
}
