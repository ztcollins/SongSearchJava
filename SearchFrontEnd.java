// --== CS400 Project One File Header ==--
// Name: Zachary Collins
// Email: ztcollins@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.util.Scanner;
import java.util.List;

// interface (implemented with proposal)
interface SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine);
    // Here is a sample of the minimal set of options that 
    // this front end will support:
    // SongSearch Command Menu:
    // 1. Insert New Song into Database
    // 2. Search For Song Titles by Words in those Title
    // 3. Search For Artists by Words in their Song Titles
    // 4. Display Years of Songs with Word in Title as Histogram
    // 5. Quit
}

// public class (implemented primarilly in final app week)
public class SearchFrontEnd implements SearchFrontEndInterface {
    @Override
    public void run(SearchBackEndInterface searchEngine) {
        boolean isRunning = true;
        while(isRunning) {
            //menu
            System.out.println("SongSearch Command Menu: (enter a number to continue)");
            System.out.println("1. Insert New Song into Database");
            System.out.println("2. Search For Song Titles by Words in those Title");
            System.out.println("3. Search For Artists by Words in their Song Titles");
            System.out.println("4. Display Years of Songs with Word in Title as Histogram");
            System.out.println("5. Quit");

            //menu input
            Scanner scnr = new Scanner(System.in);
            String option = scnr.next();

            //Insert New Song into Database (1)
            if(option.equals("1")) {
                insertSong(searchEngine);
            }
            //Search For Song Titles by Words in those Title (2)
            else if(option.equals("2")) {
                searchSongTitles(searchEngine);
            }
            //Search For Artists by Words in their Song Titles (3)
            else if(option.equals("3")) {
                searchArtists(searchEngine);
            }
            //Display Years of Songs with Word in Title as Histogram (4)
            else if(option.equals("4")) {
                yearHistogram(searchEngine);
            }
            //Quit (end) (5)
            else if(option.equals("5")) {
                exitProgram(isRunning);
            }
            //Misinput (loop) (etc.)
            else {
               misInput();
            }

        }
    }

    //HELPER METHODS

    public void insertSong(SearchBackEndInterface searchEngine) {
        System.out.println("Please enter song Title:");
        Scanner scnr = new Scanner(System.in);
        String songTitle = scnr.next();

        System.out.println("Please enter song Author:");
        String songArtist = scnr.next();

        int songYear = 0;

        System.out.println("Please enter song year: (Example: 1996)");
        try {
            songYear = scnr.nextInt();
        } catch (Exception e) {
            System.out.println("error not a year.");
        }

        try {
            SongData newSong = new SongData(songTitle, songYear, songArtist);
            searchEngine.addSong(newSong);
        } catch (Exception e) {
            System.out.println("ERROR: SONG COULD NOT BE ADDED");;
        }
        System.out.println("Success! song added.");
    }

    public void searchSongTitles(SearchBackEndInterface searchEngine) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter title word to search for song:");
        String wordToSearch = scnr.next();

        List<String> listSearch;
        listSearch = searchEngine.findTitles(wordToSearch);

        System.out.println("Search Results:");
        for(int i = 0; i < listSearch.size(); i++) {
            System.out.println((i+1) + ". " + listSearch.get(i));
        }
    }

    public void searchArtists(SearchBackEndInterface searchEngine) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter word to search for artists:");
        String wordToSearchArtists = scnr.next();

        List<String> listSearchArtists;
        listSearchArtists = searchEngine.findArtists(wordToSearchArtists);

        System.out.println("Search Results:");
        for(int i = 0; i < listSearchArtists.size(); i++) {
            System.out.println((i+1) + ". " + listSearchArtists.get(i));
        }
    }

    public void yearHistogram(SearchBackEndInterface searchEngine) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter title word to search for year:");
        String titleToSearchforYear = scnr.next();

        boolean hasHitOnce = false;
        System.out.println("Histogram of the years for the title searched:");
        //loop for all years (up until next year)
        for(int i = 0; i < 2022; i++) {
            int yearToSearch = i;
            int numberofSongsinYear = searchEngine.findNumberOfSongsInYear(
                titleToSearchforYear, yearToSearch);
            if(numberofSongsinYear > 0) {
                hasHitOnce = true;
                System.out.print(yearToSearch + ": " + numberofSongsinYear);
                for(int j = 0; j < numberofSongsinYear; j++) {
                    System.out.print(" []");
                }
                System.out.println();
            }
        }
        if(hasHitOnce == false) {
            System.out.println("no matches found.");
        }
    }

    public void exitProgram(boolean isRunning) {
        System.out.println("Quitting SongSearch...");
        isRunning = false;
        System.exit(0);
    }

    public void misInput() {
        System.out.println();
        System.out.println("Input could not be understood. Please input 1-5 and then ENTER");
        System.out.println();
    }

}



// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchFrontEndPlaceholder implements SearchFrontEndInterface {
    public void run(SearchBackEndInterface searchEngine) {
        System.out.println("PLACEHOLDER MENU.");
        System.out.println("SongSearch Command Menu: (enter a number to continue)");
        System.out.println("1. Insert New Song into Database");
        System.out.println("2. Search For Song Titles by Words in those Title");
        System.out.println("3. Search For Artists by Words in their Song Titles");
        System.out.println("4. Display Years of Songs with Word in Title as Histogram");
        System.out.println("5. Quit");
    }
}