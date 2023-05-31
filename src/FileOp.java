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

    /* Reads the list of files in a dir and returns map of filenames by size.
    *  Additionally, recursively goes over subdirectories.
    */
    public static Map<String, Long> readFiles(String path, boolean recursive) {
        File selectedDir = new File(CWD, path);

        if (!selectedDir.exists()) {
            System.out.printf("The given directory %s doesn't exist, exiting.", path);
            System.exit(0);
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
                if (file.isDirectory()) {
                    if (recursive) {
                        filesMap.putAll(readFiles(path + "/" + file.getName(), true));
                    }
                    continue;
                }
                currPath = file.toPath();
                size = Files.size(currPath);
                filesMap.put(path + "/" + file.getName(), size);
            }
        } catch (IOException e) {
            System.out.print("There was an error when reading file, exiting.");
            System.exit(0);
        }

        return filesMap;
    }
}
