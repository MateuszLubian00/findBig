/* This part of the program interacts with files.
*
*
*/

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Map;
import java.util.TreeMap;

public class FileOp {
    /** The current working directory. */
    public static File CWD = new File(System.getProperty("user.dir"));

    // File.readAttribute(path, "size")

    /* Checks if the given path is valid, and set it to CWD. */
    public static void newCWD(String path) {
        File newDir = null;

        try {
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

    /* Reads the list of files in a dir and returns map of filenames by size. */
    public static Map<String, Long> readFiles(String path) {
        File selectedDir = new File(CWD, path);
        if (!selectedDir.exists()) {

        }
        File[] files = selectedDir.listFiles();
        if (files.length == 0) {
            return new TreeMap<>();
        }
        Map<String, Long> filesMap = new TreeMap<>(){};
        long size;
        Path currPath;

        try {
            for (File file : files) {
                currPath = file.toPath();
                size = Files.size(currPath);
                filesMap.put(file.getName(), size);
            }
        } catch (IOException e) {
            System.out.print("There was an error when reading file, exiting.");
            System.exit(0);
        }

        return filesMap;
    }
}
