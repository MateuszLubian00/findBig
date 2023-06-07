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
    /* Show hidden files and enter hidden directories. */
    private static boolean showHidden = false;
    /* Given size to search for. */
    private static String targetSize = null;

    public static void main(String[] args) {

        if (args.length > 0) {
            parseCommands(args);
        }

        Logic.listFiles(recursion, descOrder, convert, showHidden, targetSize);

    }

    /* Checks arguments one by one and sets the appropriate variables. */
    private static void parseCommands(String[] commands){
        for (String option: commands) {
            if (option.startsWith("-")) {
                String letter = option.substring(1, 2);
                switch (letter) {
                    case "n":
                        recursion = false;
                        break;
                    case "s":
                        descOrder = false;
                        break;
                    case "b":
                        convert = false;
                        break;
                    case "h":
                        showHidden = true;
                        break;
                    case "f":
                        targetSize = option.substring(3);
                        break;
                    default:
                        System.out.printf("Unrecognized option %s, exiting.", option);
                        System.exit(0);
                }
            } else {
                // Assume the input is for a different PATH
                FileOp.newCWD(option);
            }
        }
    }
}