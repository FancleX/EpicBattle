package Weapon;

import Frame.Background;
import Frame.GameFrame;
import Frame.StaticValue;

import java.awt.image.BufferedImage;


import java.awt.Graphics;

public class Bullet {

    private int x;
    private int y;
    private boolean isRight = true;
    private BufferedImage bulletLeft = StaticValue.bulletLeft;
    private BufferedImage bulletRight = StaticValue.bulletRight;

    public Bullet(int x, int y, boolean isRight) {
        this.x = x;
        this.y = y;
        this.isRight = isRight;
        bulletMoving();
    }

    public void paint(Graphics graphics) {
        if (isRight) {
            graphics.drawImage(bulletRight, x, y, null);
            x += 5;
        } else {
            graphics.drawImage(bulletLeft, x, y, null);
            x -= 5;
        }
    }
    
    public void bulletMoving() {
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


    





    
}
