/**
 * @codeImplementation Zhicun Chen
 * @characterActionDesign Yiqian Huang
 */

package music;

import jaco.mp3.player.MP3Player;
import java.io.File;

/**
 * Music class will play background music when the game starts.
 */
public class Music {
    // add music to GrameFrame
    public Music() {
        // get path of music file
        String path = System.getProperty("user.dir") + "/src/music/music.mp3";
        // instantiate a mp3 player
        MP3Player player = new MP3Player(new File(path));
        // repeat playing music until the game ends
        player.setRepeat(true);
        // start playing
        player.play();
    }
}
