
import java.util.List;
  
public class SongSearchApp {
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the SongSearchApp of Group CH Team Red!");
        //List<SongDataInterface> songs = new SongLoader().loadAllFilesInDirectory("./data/");
        SearchBackEndInterface engine = new SearchBackEnd("./data/");
        //for(SongDataInterface song : songs) engine.addSong(song);
        SearchFrontEndInterface ui = new SearchFrontEnd();
        ui.run(engine);
    }
}
