/* This is the class that calls methods from FileOp class, as well as deals with outputting data to user. */

import java.util.*;

public class Logic {
    /* List of unit names converted from B to nB. */
    private static final String[] unitsNB = {"B", "KB", "MB", "GB", "TB"};
    /* List of unit names converted from B to niB. */
    private static final String[] unitsNiB = {"B", "KiB", "MiB", "GiB", "TiB"};

    /* Creates one map of files. */
    public static void listFiles(boolean recursion, boolean descOrder, boolean convert, boolean showHidden, String targetSize) {
        TreeSet<Map.Entry<String, Long>> filesMap;
        filesMap = FileOp.readFiles("", recursion, descOrder, showHidden);

        if (targetSize != null) {
           Long bytes = convertToBytes(targetSize);
           filesMap = closeToSize(filesMap, bytes);
        }

        printFiles(filesMap, convert);
    }

    /* Print out the list of files. */
    private static void printFiles(TreeSet<Map.Entry<String, Long>> filesMap, boolean convert) {
        int unit = 0;
        float size;
        for (Map.Entry<String, Long> file : filesMap) {
            size = Float.parseFloat(file.getValue().toString());
            while (convert && size >= 1024) {
                unit++;
                size = size / 1024;
            }

            // Filename - Size
            System.out.printf("%s - %.2f %s\n", file.getKey(), size, unitsNiB[unit]);
            unit = 0;
        }
    }

    /* Trim the list and order elements to better fit the target search.
    *  Note: ordering given at start of the program is ignored.
    */
    public static TreeSet<Map.Entry<String, Long>> closeToSize(TreeSet<Map.Entry<String, Long>> filesMap, Long target) {
        TreeSet<Map.Entry<String, Long>> tree = new TreeSet<>(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                // Smaller distance goes to the right branch
                // Note: returning 0 discards entry, don't do that
                long dist1 = Math.abs(target - e1.getValue());
                long dist2 = Math.abs(target - e2.getValue());

                if (dist1 < dist2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        long lowerBound = (long) (target * 0.8);
        long upperBound = (long) (target * 1.2);

        for (Map.Entry<String, Long> entry : filesMap) {
            if (entry.getValue() >= lowerBound && entry.getValue() <= upperBound) {
                tree.add(entry);
            }
        }

        return tree;
    }

    /* Converts from a given size to the size in Bytes. */
    private static Long convertToBytes(String size){
        // Don't trust user with correct capitalization
        size = size.toLowerCase();
        String targetUnit;
        String[] units = unitsNB;
        long conversion = 1000L;

        if (size.length() == 1 || size.charAt(size.length() - 1) != 'b') {
            // Special case if it is a single number or the units were not provided
            // Assume it is a number written in Bytes
            targetUnit = "b";
            // Fixing size so the result is calculated properly
            size = size + targetUnit;
        } else {
            char secondLast = size.charAt(size.length() - 2);

            if (secondLast == 'i') {
                units = unitsNiB;
                conversion = 1024L;
                targetUnit = size.substring(size.length() - 3);
            } else {
                try {
                    // Special case for single character unit
                    // If this gives an error, we know target unit have length of 2
                    Long.parseLong(String.valueOf(secondLast));
                    // We know target unit is Bytes, but just in case user wrote something else...
                    targetUnit = size.substring(size.length() - 1);
                } catch (NumberFormatException e) {
                    targetUnit = size.substring(size.length() - 2);
                }
            }
        }

        long result = 0L;
        try {
            result = Long.parseLong(size.substring(0, size.length() - targetUnit.length()));
        } catch (NumberFormatException e) {
            System.out.printf("The given size \"%s\" is malformed, exiting.", size);
            System.exit(0);
        }

        // Making sure the target unit was found.
        boolean found = false;

        // Conversion happens here
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
