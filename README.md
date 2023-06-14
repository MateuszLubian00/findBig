# findBig

A simple program that lets you search for the biggest or smallest files in a given directory. Also supports searching for a file with a particular size.

## Usage

By default the program will look for all non hidden files, including in non hidden subdirectories, within the current working directory and output a list of up to 20 files with decreasing order based on size. The output might be altered using arguments:

1. `"PATH"` - changes the directory used to start searching.
2. `-n` - no recursion; don't look into subdirectories.
3. `-s` - output smallest files first.
4. `-f="SIZE"` - output only files that are close to the given size. Ignores the ordering option and instead shows the files closest to the given size. 
5. `-b` - show the output in Bytes, instead of larger units.
6. `-h` - output hidden files, as well as search for files in hidden directories.
7. `-l="LIMIT"` - limit the amount of files outputted to the given amount. Enter -1 to disable.

Please add a spacebar between each argument. When specifying a `PATH` that contains spaces, encompass it with quotation marks. When specifying `SIZE`, please don't put additional spaces and write the unit at the end. Without a specified unit, the `SIZE` is treated as Bytes. Accepted units are up to (including) TB and TiB, excluding bits. Please note that default Windows apps incorrectly type in the metric units, while actually using IEC units. If you are unsure what that means, please read [Wikipedia Byte](https://en.wikipedia.org/wiki/Byte) article, under multiple-byte units section.