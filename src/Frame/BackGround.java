package Frame;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Background {
    
    // current background
    private BufferedImage backgroundImage = null;
    // current scence
    private int currentScence;
    // if is the final scence
    private boolean isFinal;
    // store all obstacles
    private List<Obstacle> obstacleList = new ArrayList<>();

    
    public Background() {

    }

    public Background(int currentScence, boolean isFinal) {
        this.currentScence = currentScence;
        this.isFinal = isFinal;
        
        if (isFinal) {
            backgroundImage = StaticValue.background3;
        } else {
            if (currentScence == 1) {
                backgroundImage = StaticValue.background1;
            } else {
                backgroundImage = StaticValue.background2;
            }
        }

    }


    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public int getCurrentScence() {
        return currentScence;
    }

    public boolean isFinal() {
        return isFinal;
    }


}
