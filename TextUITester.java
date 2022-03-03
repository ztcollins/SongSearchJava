import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class can be used to test text based user interactions by 1) specifying
 * a String of text input (that will be fed to System.in as if entered by the user),
 * and then 2) capturing the output printed to System.out and System.err in String
 * form so that it can be compared to the expect output.
 * @author dahl
 * @date 2021.10
 */
public class TextUITester {

    /**
     * This main method demonstrates the use of a TextUITester object to check
     * the behavior of the following run method.
     * @param args from the commandline are not used in this example
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        /* IGNORE (ORIGINAL CODE)
        // 1. Create a new TextUITester object for each test, and
        // pass the text that you'd like to simulate a user typing as only argument.
        TextUITester tester = new TextUITester("apple\n3.14\nq\n");

        // 2. Run the code that you want to test here:
        run(); // (this code should read from System.in and write to System.out)

        // 3. Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        if(output.startsWith("Welcome to the run program.") && 
           output.contains("apple4.14"))
            System.out.println("Test passed.");
        else
            System.out.println("Test FAILED.");
        */

        //testAddSongUI (1)
        System.out.println("testAddSongUI:");
        TextUITester testSongUI = new TextUITester("1\nsong1\nartists1\n2021\n");
        
        testAddSongUI();
        
        String testSongUIoutput = testSongUI.checkOutput();
        if(testSongUIoutput.startsWith("Please enter song Title:") && 
        testSongUIoutput.contains("Success!")) {
            System.out.println("test passed.");
        }
        else {
            System.out.println("test failed.");
        }
        System.out.println();

        //testSongSearchTitlesUI (2)
        System.out.println("testSongSearchTitlesUI:");
        TextUITester testSongSearchTitlesUI = new TextUITester("2\nsong1\n");

        testSongSearchTitlesUI();
        
        String testSongSearchTitlesOutput = testSongSearchTitlesUI.checkOutput();
        if(testSongSearchTitlesOutput.startsWith("Please enter title word to search for song:") && 
        testSongSearchTitlesOutput.contains("Search Results:")) {
            System.out.println("test passed.");
        }
        else {
            System.out.println("test failed.");
        }
        System.out.println();

        //testYearHistogramUI (3)
        System.out.println("testYearHistogramUI:");
        TextUITester testYearHistogramUI = new TextUITester("4\nsong1\n");
        
        testYearHistogramUI();
        
        String testYearHistogramUIoutput = testYearHistogramUI.checkOutput();
        if(testYearHistogramUIoutput.startsWith("Please enter title word to search for year:") && 
        testYearHistogramUIoutput.contains("Histogram of the years for the title searched:")) {
            System.out.println("test passed.");
        }
        else {
            System.out.println("test failed.");
        }
        System.out.println();
    }   
    
    /**
     * This is the code being tested by the main method above.
     * It 1) prints out a welcome message, 
     *    2) reads a String, a double, and a character from System.in, and then
     *    3) prints out the string followed by a number that is one greater than that double,
     *       if the character that it read in was a (lower case) 'q'.
     */
    public static void run() {
        // Note to avoid instantiating more than one Scanner reading from System.in in your code!
        Scanner in = new Scanner(System.in); 
        System.out.println("Welcome to the run program.");
        System.out.println("Enter a string, a double, and then q to quit: ");
        String s = in.nextLine();
        double d = in.nextDouble(); in.nextLine(); // read newline after double
        char c = in.nextLine().charAt(0);
        if(c == 'q')
            System.out.println(s + (d + 1.0));
        in.close();
    }

    public static void testAddSongUI() throws IOException {
        SearchBackEndInterface engine = new SearchBackEnd("");
        SearchFrontEnd ui = new SearchFrontEnd();
        ui.insertSong(engine);
    }

    public static void testSongSearchTitlesUI() throws IOException {
        SearchBackEndInterface engine = new SearchBackEnd("");
        SearchFrontEnd ui = new SearchFrontEnd();
        ui.searchSongTitles(engine);
    }
    public static void testYearHistogramUI() throws IOException {
        SearchBackEndInterface engine = new SearchBackEnd("");
        SearchFrontEnd ui = new SearchFrontEnd();
        ui.yearHistogram(engine);
    }

    // Below is the code that actually implements the redirection of System.in and System.out,
    // and you are welcome to ignore this code: focusing instead on how the constructor and
    // checkOutput() method is used int he example above.

    private PrintStream saveSystemOut; // store standard io references to restore after test
    private PrintStream saveSystemErr;
    private InputStream saveSystemIn;
    private ByteArrayOutputStream redirectedOut; // where output is written to durring the test
    private ByteArrayOutputStream redirectedErr;

    /**
     * Creates a new test object with the specified string of simulated user input text.
     * @param programInput the String of text that you want to simulate being typed in by the user.
     */
    public TextUITester(String programInput) {
        // backup standard io before redirecting for tests
        saveSystemOut = System.out;
        saveSystemErr = System.err;
        saveSystemIn = System.in;    
        // create alternative location to write output, and to read input from
        System.setOut(new PrintStream(redirectedOut = new ByteArrayOutputStream()));
        System.setErr(new PrintStream(redirectedErr = new ByteArrayOutputStream()));
        System.setIn(new ByteArrayInputStream(programInput.getBytes()));
    }

    /**
     * Call this method after running your test code, to check whether the expected
     * text was printed out to System.out and System.err.  Calling this method will 
     * also un-redirect standard io, so that the console can be used as normal again.
     * 
     * @return captured text that was printed to System.out and System.err durring test.
     */
    public String checkOutput() {
        try {
            String programOutput = redirectedOut.toString() + redirectedErr.toString();
            return programOutput;    
        } finally {
            // restore standard io to their pre-test states
            System.out.close();
            System.setOut(saveSystemOut);
            System.err.close();
            System.setErr(saveSystemErr);
            System.setIn(saveSystemIn);    
        }
    }
}