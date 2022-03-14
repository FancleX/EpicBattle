package Weapon;

import Frame.Background;
import Frame.GameFrame;
import java.awt.image.BufferedImage;

import java.awt.Graphics;

public class Bullet {

    private int x;
    private int y;
    private boolean isRight = true;

    public Bullet(int x, int y, boolean isRight) {
        this.x = x;
        this.y = y;
        this.isRight = isRight;
    }

    // public void paint(Graphics graphics) {
    //     if (isRight) {
    //         graphics.drawImage(image, x, y, frame);
    //         x += 60;
    //     } else {
    //         graphics.drawImage(image, x, y, frame);
    //         x -= 60;
    //     }
    // }
    
    public void bulletMoving() {
        if (isRight) {
            x += 150;
        } else {
            x -= 60;
        }
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

    





    
}
