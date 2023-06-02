/* This is the main class of this program.
* It only parses the command line arguments and passes the work onto logic class.
*/
public class Main {

    /* Search recursively through subdirectories. */
    private static boolean recursion = true;
    /* Which order to use when printing the file list. */
    private static boolean descOrder = true;
    /* Should the units of larger files be converted from B to KB etc. */
    private static boolean convert = true;
    /* Given size to search for. */
    private static int targetSize;

    public static void main(String[] args) {

        if (args.length > 0) {
            parseCommands(args);
        }

        Logic.listFiles(recursion, descOrder, convert);

    }

    private static void parseCommands(String[] commands){
        
    }
}