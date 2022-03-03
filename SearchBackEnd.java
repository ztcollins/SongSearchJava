import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
// interface (implemented with proposal)
interface SearchBackEndInterface {
    public void addSong(SongDataInterface song);
    public boolean containsSong(SongDataInterface song);
    // returns list of the titles of all songs that contain the word titleWord in their song title
    public List<String> findTitles(String titleWord);
    // returns list of the artists of all songs that contain the word titleWord in their song title
    public List<String> findArtists(String titleWord);
    // returns the number of songs that contain the word titleWord in their song title, and were published in year
    public int findNumberOfSongsInYear(String titleWord, int year);
}
// public class (implemented primarily in final app week)
public class SearchBackEnd implements SearchBackEndInterface {
    SongLoaderInterface loader;
    List<SongDataInterface> songList;
    HashtableMap<String, List<SongDataInterface>> table;        //table of words from titles (keys) and lists of songs which contain those words in their titles (values)
    public SearchBackEnd(String directoryPath) throws IOException {
        loader = new SongLoaderPlaceholder();
        if (directoryPath.equals("")) {
            songList = new LinkedList<>();
        } else {
            songList = loader.loadAllFilesInDirectory(directoryPath);
        }
        table = new HashtableMap<>();
        for (int i = 0; i < songList.size(); i++) {
            List<String> titleWords = getWords(songList.get(i).getTitle());
            for (int j = 0; j < titleWords.size(); j++) {
                if (!(table.containsKey(titleWords.get(j))))
                    table.put(titleWords.get(j), getValueList(titleWords.get(j)));
            }
        }
    }
    
    @Override
    public void addSong(SongDataInterface song) {
        if (!(songList.contains(song))) {
            songList.add(song);
            table.clear();
            for (int i = 0; i < songList.size(); i++) {
                addSong(songList.get(i));
            }
        }
        List<String> titleWords = getWords(song.getTitle());
        for (int i = 0; i < titleWords.size(); i++) {
            if (!(table.containsKey(titleWords.get(i))))
                table.put(titleWords.get(i), getValueList(titleWords.get(i)));
        }
    }
    @Override
    public boolean containsSong(SongDataInterface song) {
        return songList.contains(song);
    }
    @Override
    public List<String> findTitles(String titleWord) {
        List<String> titleList = new LinkedList<>();
        for (int i = 0; i < songList.size(); i++) {
            String title = songList.get(i).getTitle();
            if (title.contains(titleWord)) {
                titleList.add(title);
            }
        }
        return titleList;
    }
    @Override
    public List<String> findArtists(String titleWord) {
        List<String> artistList = new LinkedList<>();
        for (int i = 0; i < songList.size(); i++) {
            SongDataInterface song = songList.get(i);
            if (song.getTitle().contains(titleWord) && !(artistList.contains(song.getArtist()))) {
                artistList.add(song.getArtist());
            }
        }
        return artistList;
    }
    @Override
    public int findNumberOfSongsInYear(String titleWord, int year) {
        int numSongs = 0;
        for (int i = 0; i < songList.size(); i++) {
            SongDataInterface song = songList.get(i);
            if (song.getTitle().contains(titleWord) && (song.getYearPublished() == year)) {
                numSongs++;
            }
        }
        return numSongs;
    }
    //helper methods
    //returns the list of all songs with word in their title
    private List<SongDataInterface> getValueList(String word){
        List<SongDataInterface> list = songList;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (!(list.get(i).getTitle().contains(word))) {
                list.remove(i);
            }
        }
        return list;
    }
    //returns list of all words in a given title
    private List<String> getWords(String dirtyTitle) {
        List<String> words = new LinkedList<>();
        String title = cleanTitle(dirtyTitle);
        if (title.isBlank()) {
            return null;
        }
        int i = 0;
        int numWords = 1;
        for (i = 0; i < title.length(); i++) {
            if (Character.isWhitespace(title.charAt(i))) {
                numWords++;
            }
        }
        int[] whiteIndices =  new int[numWords];
        int j = 0;
        for (i = 0; i < title.length(); i++) {
            if (Character.isWhitespace(title.charAt(i))) {
                whiteIndices[j] = i;
                j++;
            }
        }
        for (i = 0; i < numWords; i++) {
            words.add(title.substring(0,whiteIndices[i]));
            title = title.substring(whiteIndices[i] + 1);
        }
        return words;
    }
    //removes all leading whitespace, trailing whitespace, and instances of two or more consecutive whitespace char

    private String cleanTitle(String title) {
        int i = 0;
        int startIndex = 0;
        int endIndex = 0;
        for (i = 0; i < title.length(); i++) {
            if (!(Character.isWhitespace(title.charAt(i)))) {
                startIndex = i;
                break;
            }
        } for (i = title.length() - 1; i >= 0; i++) {
            if (!(Character.isWhitespace(title.charAt(i)))) {
                endIndex = i;
                break;
            }
        }
        title = title.substring(startIndex, endIndex + 1);
        for (i = 0; i < title.length() - 1; i++) {
            if ((Character.isWhitespace(title.charAt(i)) && (Character.isWhitespace(title.charAt(i+1))))) {
                title = title.substring(0, i) + title.substring(i);
            }
        }
        return title;
    }
}
// placeholder(s) (implemented with proposal, and possibly added to later)
class SearchBackEndPlaceholder implements SearchBackEndInterface {
    private SongDataInterface onlySong;
    public void addSong(SongDataInterface song) {
        this.onlySong = song;
    }
    public boolean containsSong(SongDataInterface song) {
        return onlySong.equals(song);
    }
    public List<String> findTitles(String titleWord) {
        List<String> titles = new LinkedList<>();
        if(onlySong.getTitle().contains(titleWord))
            titles.add(onlySong.getTitle());
        return titles;
    }
    public List<String> findArtists(String titleWord) {
        List<String> artists = new LinkedList<>();
        if(onlySong.getArtist().contains(titleWord))
            artists.add(onlySong.getArtist());
        return artists;
    }
    public int findNumberOfSongsInYear(String titleWord, int year) {
        if(onlySong.getYearPublished() == year) return 1;
        return 0;
    }
}