/**
 * @teamMemebers Zhicun Chen, Yiqian Huang 
 */

package weapon;

import frame.StaticValue;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import java.awt.Graphics;

/**
 * Bullet class creates bullets for enemies' use.
 */
public class Bullet {

    // coordinate of bullet
    private int x;
    private int y;
    // orientation of bullet
    private boolean isRight = true;
    private BufferedImage bulletLeft = StaticValue.bulletLeft;
    private BufferedImage bulletRight = StaticValue.bulletRight;
    // collision volume of bullet
    private int width = 56;
    private int height = 24;

    /**
     * Instantiate a bullet.
     * 
     * @param x       x-axis coordinate of the bullet
     * @param y       y-axis coordinate of the bullet
     * @param isRight orientation of the bullet
     */
    public Bullet(int x, int y, boolean isRight) {
        this.x = x;
        this.y = y;
        this.isRight = isRight;
        // bullets are fired at a certain distance from the enemy
        setBulletPosition();
    }

    /**
     * Draw motion of bullet.
     * 
     * @param graphics brush
     */
    public void paint(Graphics graphics) {
        if (isRight) {
            graphics.drawImage(bulletRight, x, y, null);
            x += 10;
        } else {
            graphics.drawImage(bulletLeft, x, y, null);
            x -= 10;
        }
    }

    /**
     * Relocate the bullet to a proper location.
     */
    public void setBulletPosition() {
        if (isRight) {
            x += 60;
        } else {
            x -= 60;
        }
        y += 20;
    }

    /**
     * Set x-axis coordinate of the bullet.
     * 
     * @param x x-axis coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get x-axis coordinate of the bullet.
     * 
     * @return x-axis coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Set y-axis coordinate of the bullet.
     * 
     * @param y y-axis coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get y-axis coordinate of the bullet.
     * 
     * @return y-axis coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Set the orientation of the bullet.
     * 
     * @param isRight true if bullet faces to right
     */
    public void isRight(boolean isRight) {
        this.isRight = isRight;
    }

    /**
     * Get the orientation of the bullet.
     * 
     * @return true if bullet faces to right
     */
    public boolean isRight() {
        return isRight;
    }

    /**
     * Wrap the bullet by a rectangle.
     * 
     * @return rectangle
     */
    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
