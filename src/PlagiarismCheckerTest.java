import org.junit.Test;
import org.junit.jupiter.api.Timeout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlagiarismCheckerTest {

    private String doc1;
    private String doc2;

    @Test
    public void testCorrectSmall() {
        setTestData(0);
    }

    @Test
    public void testCorrectMed() {
        setTestData(1);
    }

    @Test
    @Timeout(value = 50, unit = TimeUnit.MILLISECONDS)
    public void testEfficientLarge() {
        setTestData(2);
    }

    @Test
    @Timeout(value = 250, unit = TimeUnit.MILLISECONDS)
    public void testEfficientLargest() {
        setTestData(3);
    }

    @Test
    @Timeout(value = 70, unit = TimeUnit.MILLISECONDS)
    public void testEdgeCase() {
        setTestData(4);
    }

    @Test
    public void testFindindAllStringsSmall() {
        /** This should print two messages:
         *      ABA
         *      ABD
         */
        setTestData(5);
    }

    @Test
    public void testFindindAllStringsLarger() {
        /** This should print four messages:
         *      A COT ALINE
         *      A LOT ALINE
         *      A COT ALIKE
         *      A LOT ALIKE
         */
        setTestData(6);
    }

    @Test
    public void allStringsMore() {
        /** This should print four messages:
         *      Menlo School's mission is to empower students to ee and expand their interests, ach their fullest potential, l the skills er for success in college, and become ethical, responsible, and engaged rs  ever wider communities.
         *      Menlo School's mission is to empower students to ee and expand their interests, ach their fullest potential, l the skills ee for success in college, and become ethical, responsible, and engaged rs  ever wider communities.
         *      Menlo School's mission is to empower students to ee and expand their interests, ach their fullest potential, d the skills er for success in college, and become ethical, responsible, and engaged rs  ever wider communities.
         *      Menlo School's mission is to empower students to ee and expand their interests, ach their fullest potential, d the skills ee for success in college, and become ethical, responsible, and engaged rs  ever wider communities.
         */
        setTestData(7);
    }

    private void setTestData(int testNumber) {
        // Open files
        try {
            BufferedReader testReader = new BufferedReader(new FileReader("test_files/" + testNumber + ".txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader("test_files/" + testNumber + "_answers.txt"));

            // Read in the data, then run.
            int answer = Integer.parseInt(answerReader.readLine());
            loadTest(testReader);
            assertEquals(answer, PlagiarismChecker.longestSharedSubstring(doc1, doc2),
                    "Test " + testNumber + " failed: should return " + answer);

        } catch (IOException e) {
            System.out.println("Error opening test file #" + testNumber);
            e.printStackTrace();
        }
    }

    private void loadTest(BufferedReader br) {

        try {
            doc1 = br.readLine();
            doc2 = br.readLine();

        } catch (IOException e) {
            System.out.println("Error opening test file");
            e.printStackTrace();
        }
    }
}
