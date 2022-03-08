package Frame;
import java.awt.image.BufferedImage;

public class Background {
    
    // current background
    private BufferedImage backgroundImage = null;

    // current scence
    private int currentScence;

    // if is the final scence
    private boolean isFinal;
    
    public Background() {

    }

    public Background(int currentScence, boolean isFinal) {
        this.currentScence = currentScence;
        this.isFinal = isFinal;
        
        if (isFinal) {
            
        }

    }


}
