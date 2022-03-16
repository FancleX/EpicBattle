package music;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Music {
    public Music () throws FileNotFoundException, JavaLayerException {
        Player player;
        String path = System.getProperty("user.dir") + "/src/music/music.wav";
        BufferedInputStream music = new BufferedInputStream(new FileInputStream(path));
        player = new Player(music);
        player.play();
    }
}
