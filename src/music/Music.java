package music;

import jaco.mp3.player.MP3Player;
import java.io.File;

public class Music {
    // add music to GrameFrame
    public Music() {
        String path = System.getProperty("user.dir") + "/src/music/music.mp3";
        MP3Player player = new MP3Player(new File(path));
        player.setRepeat(true);
        player.play();
    }
}
