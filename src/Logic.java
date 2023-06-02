/* This is the engine of the whole program, where most of the work will be done.
*
*
*/
import java.util.*;

public class Logic {
    /* List of unit names converted from B to nB. */
    private static final String[] unitsNB = {"B", "KB", "MB", "GB", "TB"};
    /* List of unit names converted from B to niB. */
    private static final String[] unitsNiB = {"B", "KiB", "MiB", "GiB", "TiB"};

    /* Creates one map of files. */
    public static TreeSet<Map.Entry<String, Long>> listFiles(boolean recursion, boolean descOrder) {
        TreeSet<Map.Entry<String, Long>> filesMap;
        filesMap = FileOp.readFiles("", recursion, descOrder);

        printFiles(filesMap);
        return filesMap;
    }

    /* Print out the list of files. */
    private static void printFiles(TreeSet<Map.Entry<String, Long>> filesMap) {
        int unit = 0;
        float size;
        for (Map.Entry file : filesMap) {
            size = Float.parseFloat(file.getValue().toString());
            while (size >= 1024) {
                unit++;
                size = size / 1024;
            }

            System.out.printf("%s - %.2f %s\n", file.getKey(), size, unitsNB[unit]);
            unit = 0;
        }
    }
}
