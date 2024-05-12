import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Binary Search program but in Java
 *
 * @author Spencer Scarlett
 * @version 1.0
 * @since 2024-05-2
 */

public final class BinSearch {

    /** Private constructor to prevent instantiation. */
    private BinSearch() {
        throw new UnsupportedOperationException("Cannot instantiate");
    }

    /**
     * This is the main method.
     *
     * @param args Unused
     */
    public static void main(final String[] args) {
        final List<String> listOfStrings = new ArrayList<String>();
        // empty variable for later
        try {
            // preps file access and writing/reading
            final File fileInput = new File("input.txt");
            final File fileOutput = new File("output.txt");
            final Scanner sc = new Scanner(fileInput);
            final FileWriter fW = new FileWriter(fileOutput);
            final PrintWriter write = new PrintWriter(fW);

            // reads each line of file
            while (sc.hasNextLine()) {
                final String strLine = sc.nextLine();
                listOfStrings.add(strLine);
            }
            final String[] arrayOfStr = listOfStrings.toArray(new String[0]);

            // for loop to remove spaces with the array to make it readable and find the target number
            for (int i = 0; i < arrayOfStr.length; i += 2) {
                int target = Integer.parseInt(arrayOfStr[i]);
                String[] numStrings = arrayOfStr[i + 1].split(" ");
                int[] arrayNum = new int[numStrings.length];
                for (int j = 0; j < numStrings.length; j++) {
                    arrayNum[j] = Integer.parseInt(numStrings[j]);
                }

                // sort the array
                Arrays.sort(arrayNum);

                // output message for if the target was found or not
                // but also shows the index found at
                final int resultFinal = RecBinSearch(target, arrayNum, 0, arrayNum.length - 1);
                if (resultFinal == -1) {
                    write.println("Target " + target + " was not found within the array");
                } else {
                    write.println("Target " + target + " was found at index " + resultFinal + " within the array");
                }
            }
        // closes resources
            write.close();
            sc.close();

        } catch (IOException | NumberFormatException err) {
            // For when no input file is found.
            System.err.println("Error: " + err.getMessage());
        }
        // program finished
        System.out.println("Done");

    }
    
    /**
    * this function uses recursion to find the search number in the array
    *
    * @param target    target number
    * @param arrayNum  array that we will be using to search
    * @param low       lowest number point in array
    * @param high      highest number point in array
    * @return          index point of target if found
    */
    public static int RecBinSearch(int target, int[] arrayNum, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if (arrayNum[mid] == target) {
                return mid;
            } else if (arrayNum[mid] > target) {
                return RecBinSearch(target, arrayNum, low, mid - 1);
            } else {
                return RecBinSearch(target, arrayNum, mid + 1, high);
            }
        } else {
            return -1;
        }
    }
}
