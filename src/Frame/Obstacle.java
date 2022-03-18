package Frame;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Obstacle {
    // coordinate
    private int x;
    private int y;
    // type of obstacle
    private int type;
    // render image
    private BufferedImage image = null;
    // current scence
    private Background background = new Background();
    // height
    private int height;
    // width
    private int width;

    public Obstacle(int x, int y, int width, int height, int type, Background background) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
