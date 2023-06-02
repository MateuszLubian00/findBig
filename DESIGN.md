# Class overview

## Main

This is the starting point for program. It parses the given arguments and calls the method `` in `Logic` class to continue.

### Fields

- `private static boolean recursion = true` - also search in subdirectories.
- `private static boolean descOrder = true` - should the order of files be descending (by size).
- `private static boolean convert = true` - if the file exceedes 1024 B, should the program convert to larger units.
- `private static int targetSize` - if this variable is initialized, perform search and output only files that are close to the given size.

### Methods

-`private static void parseCommands(String[] commands)` - parses the argument list and sets the corresponding variables.

## FileOp

This is the class that manages all access to files.

### Fields

- `public static File CWD = new File(System.getProperty("user.dir"))` - path to directory used for searching files. Defaults to current working directory.

### Methods

- `public static void newCWD(String path)` - validates the passed path by checking if it is a directory. Also changes `CWD` to this value, unless there was an error.
- `public static Map<String, Long> readFiles(String path, boolean recursive, boolean descOrder)` - goes over the list of files in a given directory and adds all of them into a TreeSet. Recursively goes inside subdirectories if needed.

## Logic

### Fields

- `` -

### Methods

- `` -
- `` -