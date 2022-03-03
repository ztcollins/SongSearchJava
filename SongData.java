// --== CS400 Project One File Header ==--
// Name: Xindi Tang
// Email: xtang89@wisc.edu
// Team: Red
// Group: CH
// TA: Harper
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>
// interface (implemented with proposal)
interface SongDataInterface {
  public String getTitle();
  public String getArtist();
  public int getYearPublished();
}
// public class (implemented primarilly in final app week)
public class SongData implements SongDataInterface {
  String title;
  int yearPublished;
  String artist;
  public SongData(String[] songData, String[] header) {
    if (header.length == 9) { // if file is "Top 500 Songs.csv"
      title = songData[0];
      String yearPublishedColumn = songData[6];
      String intValue = yearPublishedColumn.replaceAll("[^0-9]", "");
      yearPublished = Integer.parseInt(intValue);
      artist = songData[3];
    } else if (header.length == 15) { // if file is "top10s.csv"
      title = songData[1];
      yearPublished = Integer.parseInt(songData[4]);
      artist = songData[2];
    }
  }

  public SongData(String title, int yearPublished, String artist) {
    this.title = title;
    this.yearPublished = yearPublished;
    this.artist = artist;
  }
  @Override
  public String getTitle() {
    return title;
  }
  @Override
  public String getArtist() {
    return artist;
  }
  @Override
  public int getYearPublished() {
    return yearPublished;
  }
}
// placeholder(s) (implemented with proposal, and possibly added to later)
class SongDataPlaceholderA implements SongDataInterface {
  public String getTitle() {
    return "Song A Vowel";
  }
  public String getArtist() {
    return "Artist X";
  }
  public int getYearPublished() {
    return 1900;
  }
}
class SongDataPlaceholderB implements SongDataInterface {
  public String getTitle() {
    return "Song B Consonant";
  }
  public String getArtist() {
    return "Artist Y";
  }
  
  public int getYearPublished() {
    return 2000;
  }
}
class SongDataPlaceholderC implements SongDataInterface {
  public String getTitle() {
    return "Song C Consonant";
  }
  public String getArtist() {
    return "Artist X";
  }
  public int getYearPublished() {
    return 2021;
  }
}
