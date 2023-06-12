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
    public static TreeSet<Map.Entry<String, Long>> listFiles(boolean recursion, boolean descOrder, boolean convert, boolean showHidden, String targetSize) {
        TreeSet<Map.Entry<String, Long>> filesMap;
        filesMap = FileOp.readFiles("", recursion, descOrder, showHidden);

        if (targetSize != null) {
           Long bytes = convertToBytes(targetSize);
           filesMap = closeToSize(filesMap, bytes);
        }

        printFiles(filesMap, convert);
        return filesMap;
    }

    /* Print out the list of files. */
    private static void printFiles(TreeSet<Map.Entry<String, Long>> filesMap, boolean convert) {
        int unit = 0;
        float size;
        for (Map.Entry file : filesMap) {
            size = Float.parseFloat(file.getValue().toString());
            while (convert && size >= 1024) {
                unit++;
                size = size / 1024;
            }

            System.out.printf("%s - %.2f %s\n", file.getKey(), size, unitsNB[unit]);
            unit = 0;
        }
    }

    /* Trim the list and order elements to better fit the target search.
    *  Note: ordering given at start of the program is ignored.
    */
    public static TreeSet<Map.Entry<String, Long>> closeToSize(TreeSet<Map.Entry<String, Long>> filesMap, Long target) {

        return null;
    }

    /* Converts from large size to the lowest - bytes. */
    private static Long convertToBytes(String size){
        // Don't trust user with correct capitalization
        size = size.toLowerCase();
        String targetUnit;
        String[] units;
        Long conversion;

        if (size.length() == 1) {
            // At length 1 we assume it is a number written in Bytes
            targetUnit = "B";
            units = unitsNB;
            conversion = 1000L;
            // Fixing size so the result is calculated properly
            size = size + targetUnit;
        } else if (size.charAt(size.length() -2) == 'i') {
            units = unitsNiB;
            conversion = 1000L;
            targetUnit = size.substring(size.length() - 3);
        } else {
            units = unitsNB;
            conversion = 1024L;
            targetUnit = size.substring(size.length() - 2);
        }

        Long result = Long.parseLong(size.substring(0, size.length() - targetUnit.length()));
        // Making sure the target unit was found.
        boolean found = false;

        for (String unit: units) {
            unit = unit.toLowerCase();
            if (unit.equals(targetUnit)) {
                found = true;
                break;
            }
            result = result * conversion;
        }

        if (!found) {
            System.out.printf("The given unit of size \"%s\" is not recognized, exiting.", targetUnit);
            System.exit(0);
        }

        return result;
    }

}
