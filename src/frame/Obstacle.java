/**
 * @authors Zhicun Chen, Yiqian Huang  
 */

package frame;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

/**
 * Obstacle class defines the type of obstacles and obstacles' size and
 * locations in the background.
 */
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

    /**
     * Instantiate an obstacle.
     * 
     * @param x          x-axis coordinate
     * @param y          y-axis coordinate
     * @param width      width
     * @param height     height
     * @param type       type of obstacles, different type has different images
     * @param background background of the obstacle
     */
    public Obstacle(int x, int y, int width, int height, int type, Background background) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
        this.background = background;

        // get different images based on obstacle type
        image = StaticValue.obstacles.get(type);
    }

    /**
     * Get x-axis coordinate.
     * 
     * @return x-axis coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get y-axis coordinate.
     * 
     * @return y-axis coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Get type of obstalce.
     * 
     * @return type of obstacle
     */
    public int getType() {
        return type;
    }

    /**
     * Get image of obstacle.
     * 
     * @return image of obstacle
     */
    public BufferedImage getRenderedImage() {
        return image;
    }

    /**
     * Wrap obstalce by an rectangle.
     * 
     * @return rectangle of the obstacle
     */
    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Get width of the obstacle.
     * 
     * @return width of the obstacle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get height of the obstacle.
     * 
     * @return height of the obstacle
     */
    public int getHeight() {
        return height;
    }

}
