// --== CS400 Project One File Header ==--
// Name: Xindi Tang
// Email: xtang89@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

// interface (implemented with proposal)

interface SongLoaderInterface {
  public List<SongDataInterface> loadFile(String csvFilePath)
      throws FileNotFoundException, IOException;

  public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
      throws FileNotFoundException, IOException;
}
// public class (implemented primarilly in final app week)
public class SongLoader implements SongLoaderInterface {
  @Override
  public List<SongDataInterface> loadFile(String csvFilePath)
      throws FileNotFoundException, IOException {
    List<SongDataInterface> songs = new LinkedList<>();
    BufferedReader songReader = new BufferedReader(new FileReader(csvFilePath));
    // read the first row and extract the header of csv file
    String[] header = songReader.readLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
    // read the rest rows and parse different columns, store song data in a linked list
    String rawSongData = songReader.readLine();
    while (rawSongData != null) {
        String[] songData = rawSongData.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        SongData song = new SongData(songData, header);
        songs.add(song);
        rawSongData = songReader.readLine();
      }
      return songs;
    }
    @Override
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
        throws FileNotFoundException, IOException {
      // load songs stored in the file for all files in directory
      File dirPath = new File(directoryPath);
      File filesList[] = dirPath.listFiles();
      LinkedList<SongDataInterface> songsList = new LinkedList<>();
      for (File file : filesList) {
        songsList.addAll(loadFile(file.getAbsolutePath()));
      }
      return songsList;
    }
  }
  // placeholder(s) (implemented with proposal, and possibly added to later)
  class SongLoaderPlaceholder implements SongLoaderInterface {
    public List<SongDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
      List<SongDataInterface> list = new LinkedList<>();
      list.add(new SongDataPlaceholderA());
      list.add(new SongDataPlaceholderB());
      return list;
    }
    public List<SongDataInterface> loadAllFilesInDirectory(String directoryPath)
        throws FileNotFoundException {
      List<SongDataInterface> list = new LinkedList<>();
      list.add(new SongDataPlaceholderA());
      list.add(new SongDataPlaceholderB());
      list.add(new SongDataPlaceholderC());
      return list;
    }
  }