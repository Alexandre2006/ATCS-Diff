import java.util.Arrays;

/**
 * Plagiarism Checker
 * A tool for finding the longest shared substring between two documents.
 *
 * @author Zach Blick
 * @author Alexandre Haddad-Delaveau
 */
public class PlagiarismChecker {

    /**
     * This method finds the longest sequence of characters that appear in both texts in the same order,
     * although not necessarily contiguously.
     * @param doc1 the first document
     * @param doc2 the second
     * @return The length of the longest shared substring.
     */
    public static int longestSharedSubstring(String doc1, String doc2) {
        // Find the longest document
        boolean isDoc1Longer = doc1.length() > doc2.length();

        // Run the helper method with the longer document first

        // OPTION 1: Use the space and time efficient helper method (length only) - Space-Optimized Tabulation
        // return isDoc1Longer ? longestSharedSubstringHelper(doc1, doc2) : longestSharedSubstringHelper(doc2, doc1);

        // OPTION 2: Use the inefficient helper method (length and substring) - Tabulation + Backtracking
        String longestSharedSubstring = isDoc1Longer ? getlongestSharedSubstring(doc1, doc2) : getlongestSharedSubstring(doc2, doc1);
        System.out.println("Longest shared substring: " + longestSharedSubstring);
        return longestSharedSubstring.length();
    }

    private static int longestSharedSubstringHelper(String doc1, String doc2) {
        boolean currentRow = false;

        // Create 2D array to store tabulation results
        int[][] results = new int[2][doc1.length() + 1];

        // Iterate through all characters (in both docs)
        for (int x = 1; x <= doc2.length(); x++) {
            for (int y = 1; y <= doc1.length(); y++) {
                // Check if characters at current indices are equal
                if (doc2.charAt(x - 1) == doc1.charAt(y - 1)) {
                    // If they are equal, increment the previous result by 1
                    results[currentRow ? 1 : 0][y] = results[currentRow ? 0 : 1][y - 1] + 1;
                } else {
                    // Otherwise, skip a character from either document, and find the max of the two
                    results[currentRow ? 1 : 0][y] = Math.max(results[currentRow ? 1 : 0][y - 1], results[currentRow ? 0 : 1][y]);
                }
            }

            // Switch the current row
            currentRow = !currentRow;
        }

        // Return the length of the longest shared substring
        return results[currentRow ? 0 : 1][doc1.length()];
    }

    private static String getlongestSharedSubstring(String doc1, String doc2) {
        // Create 2D array to store tabulation results
        int[][] results = new int[doc1.length() + 1][doc2.length() + 1];

        // Iterate through all characters (in both docs)
        for (int x = 1; x <= doc1.length(); x++) {
            for (int y = 1; y <= doc2.length(); y++) {
                // Check if characters at current indices are equal
                if (doc1.charAt(x - 1) == doc2.charAt(y - 1)) {
                    // If they are equal, increment the previous result by 1
                    results[x][y] = results[x - 1][y - 1] + 1;
                } else {
                    // Otherwise, skip a character from either document, and find the max of the two
                    results[x][y] = Math.max(results[x - 1][y], results[x][y - 1]);
                }
            }
        }

        // Backtrack to find the longest shared substring
        StringBuilder longestSharedSubstring = new StringBuilder();
        int x = doc1.length();
        int y = doc2.length();

        while (x > 0 && y > 0) {
            // If the characters are equal, add the character to the result
            if (doc1.charAt(x - 1) == doc2.charAt(y - 1)) {
                longestSharedSubstring.insert(0, doc1.charAt(x - 1));
                x--;
                y--;
            } else {
                // Otherwise, move to the next cell with the greater value
                if (results[x - 1][y] > results[x][y - 1]) {
                    x--;
                } else {
                    y--;
                }
            }
        }

        // Return the longest shared substring
        return longestSharedSubstring.toString();
    }
}