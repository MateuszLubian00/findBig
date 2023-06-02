# findBig

A simple program that lets you search for the biggest or smallest files in a given directory. Also supports searching for a file with a particular size.

## Usage

By default the program will look for all files, including in subdirectories, within the current working directory and output a list of files with decreasing order of size. The output might be altered using arguments:

1. `"PATH"` - changes the directory used to start searching.
2. `-n` - no recursion; don't look into subdirectories.
3. `-s` - reverse the order, output smallest files first.
4. `-f=SIZE` - output only files that are close to the given size (in bytes).
5. `-b` - show the output in Bytes.