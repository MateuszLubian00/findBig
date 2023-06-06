/* This part of the program interacts with files.
*
*
*/

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileOp {
    /** The current working directory. */
    public static File CWD = new File(System.getProperty("user.dir"));

    /* Check if the given path is valid, and set it to CWD. */
    public static void newCWD(String path) {
        File absolute = null;
        File relative = null;

        try {
            absolute = new File(path);
            relative = new File(CWD, path);
        } catch(InvalidPathException e) {
            System.out.print("The given path is not valid, exiting.");
            System.exit(0);
        }

        if (absolute.exists() && absolute.isDirectory()) {
            CWD = absolute;
        } else if (relative.exists() && relative.isDirectory()) {
            CWD = relative;
        } else {
                System.out.print("The given path is not a valid directory, exiting.");
                System.exit(0);
        }
    }

    /* Reads the list of files in a dir and returns map of filenames by size.
    *  Additionally, recursively goes over subdirectories.
    */
    public static TreeSet<Map.Entry<String, Long>> readFiles(String path, boolean recursive, boolean order, boolean showHidden) {
        File selectedDir = new File(CWD, path);
        if (!selectedDir.exists()) {
            System.out.printf("The given directory %s doesn't exist, exiting.", path);
            System.exit(0);
        }

        FilenameFilter filter = (File dir, String name) -> !name.startsWith(".");
        if (showHidden) {filter = null;};

        File[] files = selectedDir.listFiles(filter);
        if (files != null && files.length == 0) {
            // Added for compatibility when adding files recursively.
            return makeSet(order);
        }

        TreeSet<Map.Entry<String, Long>> filesMap = makeSet(order);

        long size;
        Path currPath = null;

        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (recursive) {
                        filesMap.addAll(readFiles(path + File.separator + file.getName(), true, order, showHidden));
                    }
                    continue;
                }
                currPath = file.toPath();
                size = Files.size(currPath);
                filesMap.add(new AbstractMap.SimpleEntry<>(path + File.separator + file.getName(), size));
            }
        } catch (IOException e) {
            System.out.printf("There was an error when reading file %s, exiting.", currPath);
            System.exit(0);
        }

        return filesMap;
    }

    /* Helper method that returns an empty TreeSet with modified constructor to allow for
    *  value-based ordering.
    */
    private static TreeSet<Map.Entry<String, Long>> makeSet(boolean order){
        TreeSet<Map.Entry<String, Long>> tree = new TreeSet<>(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                int valueComparison = e1.getValue().compareTo(e2.getValue());
                if (order) {valueComparison = -valueComparison;}
                return valueComparison == 0 ? e1.getKey().compareTo(e2.getKey()) : valueComparison;
            }
        });

    return tree;
    }
}
