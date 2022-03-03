import java.io.IOException;

public class FrontEndDevTests {
    public static void main(String[] args) {

    }

    public boolean FrontEndDeveloper_TestAddSongUI() throws IOException {
        //testAddSongUI (1)
        System.out.println("testAddSongUI:");
        TextUITester testSongUI = new TextUITester("1\nsong1\nartists1\n2021\n");
        
        TextUITester.testAddSongUI();
        
        String testSongUIoutput = testSongUI.checkOutput();
        if(testSongUIoutput.startsWith("Please enter song Title:") && 
        testSongUIoutput.contains("Success!")) {
            return true;
        }
        else {
            System.out.println("FrontEndDeveloper_TestAddSongUI() test failed.");
            System.out.println();
            return false;
        }
    }

    public boolean FrontEndDeveloper_TestSongSearchTitlesUI() throws IOException {
        System.out.println("testSongSearchTitlesUI:");
        TextUITester testSongSearchTitlesUI = new TextUITester("2\nsong1\n");

        TextUITester.testSongSearchTitlesUI();
        
        String testSongSearchTitlesOutput = testSongSearchTitlesUI.checkOutput();
        if(testSongSearchTitlesOutput.startsWith("Please enter title word to search for song:") && 
        testSongSearchTitlesOutput.contains("Search Results:")) {
            return true;
        }
        else {
            System.out.println("FrontEndDeveloper_TestSongSearchTitlesUI() test failed.");
            System.out.println();
            return false;
        }
    }

    public boolean FrontEndDeveloper_TestYearHistogramUI() throws IOException {
        System.out.println("testYearHistogramUI:");
        TextUITester testYearHistogramUI = new TextUITester("4\nsong1\n");
        
        TextUITester.testYearHistogramUI();
        
        String testYearHistogramUIoutput = testYearHistogramUI.checkOutput();
        if(testYearHistogramUIoutput.startsWith("Please enter title word to search for year:") && 
        testYearHistogramUIoutput.contains("Histogram of the years for the title searched:")) {
            return true;
        }
        else {
            System.out.println("FrontEndDeveloper_TestYearHistogramUI() failed.");
            System.out.println();
            return false;
        }
    }
}
