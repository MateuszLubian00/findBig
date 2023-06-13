# Class overview

## Main

This is the starting point for program. It parses the given arguments and calls the method `listFiles` in `Logic` class to continue.

### Fields

- `private static boolean recursion` - also search in subdirectories.
- `private static boolean descOrder` - should the order of files be descending (by size).
- `private static boolean convert` - if the file exceedes size of 1024 B, should the program convert to larger units.
- `private static boolean showHidden` should the program look for files and output files that start with a dot.
- `private static String targetSize` - if this variable is initialized, perform search and output only files that are close to the given size.

### Methods

-`private static void parseCommands(String[] commands)` - parses the argument list and sets the corresponding variables.

## FileOp

This is the class that manages all access to files. It's class `readFiles` is used to get an ordered `TreeSet` of the files. The `TreeSet` is actually a `TreeMap` in disguise, with it's variables being `<String, Long>`, corresponding to `<Filename with path, Size>`. The reason for such implementation is the ability to use ordering based on values instead of keys. It does mean that some of the methods are broken and reqiure workarounds (such as `TreeSet.remove`), however this program uses no such methods.

### Fields

- `public static File CWD` - path to directory used for searching files. Defaults to current working directory.

### Methods

- `public static void newCWD(String path)` - validates the passed path by checking if it is a directory. Also changes `CWD` to this value, unless there was an error.
- `public static Map<String, Long> readFiles(String path, boolean recursive, boolean descOrder, boolean showHidden)` - main method of this class. It goes over the list of files in a given directory and adds all of them into a TreeSet. Recursively goes inside subdirectories if needed.
- `private static TreeSet<Map.Entry<String, Long>> makeSet(boolean order)` - helper method that returns a TreeSet that simulates a map, but with value-based item ordering.

## Logic

This class is responsible for properly decorating and outputting data to user.

### Fields

- `private static final String[] unitsNB` - increasing list of unit names for Bytes in powers of 10.
- `private static final String[] unitsNiB` - same as above, but in powers of 2.

### Methods

- `public static void listFiles(boolean recursion, boolean descOrder, boolean convert, boolean showHidden, String targetSize)` - main method of this class. It primarly calls the method `FileOp.readFiles` to get a list of files, and then passes it around to further work on it or output it.
- `private static void printFiles(TreeSet<Map.Entry<String, Long>> filesMap, boolean convert)` - prints out the data in a format `Filename with path - Size with 2 decimal spaces`. Additionally converts Byte unit found in the list to larger IEC units.
- `public static TreeSet<Map.Entry<String, Long>> closeToSize(TreeSet<Map.Entry<String, Long>> filesMap, Long target)` - creates a new `TreeSet` with ordering based on distance to target, then it adds all items from the passed on `TreeSet` as long as they are within the search bounds.
- `private static Long convertToBytes(String size)` - takes in a string that is supposed to represent a size with specified units and returns that size in Bytes.

# Persistence

This program doesn't save any data between usages.