package Frame;
import java.awt.image.BufferedImage;

public class Obstacle {
    // coordinate
    private int x;
    private int y;
    // type of obstacle
    private int type;
    // render image
    private BufferedImage image = null;
    // current scence
    private Background background = null;

    public Obstacle(int x, int y, int type, Background background) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.background = background;

        image = StaticValue.obstacles.get(type);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getRenderedImage() {
        return image;
    }


}
