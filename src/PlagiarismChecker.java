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
        // Initialize memoization table
        int[][] memo = new int[doc1.length() + 1][doc2.length() + 1];
        for (int i = 0; i <= doc1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }

        // Start recursing
        return longestSharedSubstring(doc1, doc2, 0, 0, memo);
    }

    private static int longestSharedSubstring(String doc1, String doc2, int index1, int index2, int[][] memo) {
        // Base case: Substring must be 0 if we've reached the end of either document
        if (index1 == doc1.length() || index2 == doc2.length()) {
            return 0;
        }

        // Memoization: check for cached value
        if (memo[index1][index2] != -1) {
            return memo[index1][index2];
        }

        // Check if characters match
        if (doc1.charAt(index1) == doc2.charAt(index2)) {
            // If they match, add 1 and continue
            memo[index1][index2] = 1 + longestSharedSubstring(doc1, doc2, index1 + 1, index2 + 1, memo);
        } else {
            // Otherwise, find the maximum of incrementing each index
            memo[index1][index2] = Math.max(
                    longestSharedSubstring(doc1, doc2, index1 + 1, index2, memo),
                    longestSharedSubstring(doc1, doc2, index1, index2 + 1, memo)
                    );
        }

        // Return result
        return memo[index1][index2];
    }
}