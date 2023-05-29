# Class overview

## Main

This is the starting point for program. It parses the given arguments and calls the method `` in `Logic` class to continue.

### Fields

- `private boolean recursion = true` - also search in subdirectories.
- `private boolean order = true` - order used for outputting files.
- `private int targetSize` - if this variable is initialized, perform search and output only files that are close to the given size.

### Methods

-`private static void parseCommands(String[] commands)` - parses the argument list and sets the corresponding variables.

## Files

This is the class that manages all access to files.

### Fields

- `public static File currentDir = new File(System.getProperty("user.dir"))` - path to directory used for searching files. Defaults to current working directory.

### Methods

- `public static void validateDir(String path)` - validates the passes path by checking if it is a directory. Also changes `currentDir` to this value, unless there was an error. Returns true if succeeds.

## Logic

### Fields

- `` -

### Methods

- `` -
- `` -