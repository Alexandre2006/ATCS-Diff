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

        // Return the length of the longest shared substring
        return results[doc1.length()][doc2.length()];
    }
}