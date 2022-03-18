package Weapon;

import Frame.StaticValue;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import java.awt.Graphics;

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

    public Bullet(int x, int y, boolean isRight) {
        this.x = x;
        this.y = y;
        this.isRight = isRight;
        setBulletPosition();
    }

    // draw motion of bullet
    public void paint(Graphics graphics) {
        if (isRight) {
            graphics.drawImage(bulletRight, x, y, null);
            x += 10;
        } else {
            graphics.drawImage(bulletLeft, x, y, null);
            x -= 10;
        }
    }

    // relocate the bullet to a proper location
    public void setBulletPosition() {
        if (isRight) {
            x += 60;
        } else {
            x -= 60;
        }
        y += 20;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void isRight(boolean isRight) {
        this.isRight = isRight;
    }

    public boolean isRight() {
        return isRight;
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }
}
