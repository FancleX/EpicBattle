package music;

import jaco.mp3.player.MP3Player;
import java.io.File;

public class Music {
    public Music() {
        String path = System.getProperty("user.dir") + "/src/music/music.mp3";
        new MP3Player(new File(path)).play();;
    }
}
