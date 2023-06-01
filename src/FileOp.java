/* This part of the program interacts with files.
*
*
*/

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class FileOp {
    /** The current working directory. */
    public static File CWD = new File(System.getProperty("user.dir"));

    /* Checks if the given path is valid, and set it to CWD. */
    public static void newCWD(String path) {
        File newDir = null;

        try {
            // If this is not an absolute path, java should automatically add the current working directory
            // each time a file with this path is referenced.
            newDir = new File(path);
        } catch(InvalidPathException e) {
            System.out.print("The given path is not valid, exiting.");
            System.exit(0);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            CWD = newDir;
        } else {
            System.out.print("The given path is not a valid directory, exiting.");
            System.exit(0);
        }
    }

    /* Reads the list of files in a dir and returns map of filenames by size.
    *  Additionally, recursively goes over subdirectories.
    */
    public static TreeSet<Map.Entry<String, Long>> readFiles(String path, boolean recursive, boolean order) {
        File selectedDir = new File(CWD, path);

        if (!selectedDir.exists()) {
            System.out.printf("The given directory %s doesn't exist, exiting.", path);
            System.exit(0);
        }

        File[] files = selectedDir.listFiles();
        if (files.length == 0) {
            // Added for compatibility when adding files recursively.
            return new TreeSet<Map.Entry<String, Long>>();
        }

        TreeSet<Map.Entry<String, Long>> filesMap = new TreeSet<>(new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                int valueComparison = e1.getValue().compareTo(e2.getValue());
                if (order) {valueComparison = -valueComparison;}
                return valueComparison == 0 ? e1.getKey().compareTo(e2.getKey()) : valueComparison;
            }
        });

        long size;
        Path currPath;

        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (recursive) {
                        filesMap.addAll(readFiles(path + File.separator + file.getName(), true, order));
                    }
                    continue;
                }
                currPath = file.toPath();
                size = Files.size(currPath);
                filesMap.add(new AbstractMap.SimpleEntry<>(path + File.separator + file.getName(), size));
            }
        } catch (IOException e) {
            System.out.print("There was an error when reading file, exiting.");
            System.exit(0);
        }

        return filesMap;
    }
}
