/* This part of the program interacts with files.
*
*
*/

import java.io.File;
import java.nio.file.*;
import java.util.List;
public class Files {
    /** The current working directory. */
    public static File currentDir = new File(System.getProperty("user.dir"));

    // File.readAttribute(path, "size")

    /* Checks if the given path is valid, and set it to currentDir. */
    public static void validateDir(String path) {
        File newDir = null;

        try {
            newDir = new File(path);
        } catch(InvalidPathException e) {
            System.out.print("The given path is not valid, exiting.");
            System.exit(0);
        }

        if (newDir.exists() && newDir.isDirectory()) {
            currentDir = newDir;
        } else {
            System.out.print("The given path is not a valid directory, exiting.");
            System.exit(0);
        }
    }
}
