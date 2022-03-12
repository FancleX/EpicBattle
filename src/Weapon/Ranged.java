package Weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Frame.Background;
import Frame.StaticValue;

public class Ranged implements Weapon{

    private int strenght;
    private int durability;
    // coordinate of weapon effects
    private int effectsX;
    private int effectsY;
    // effects image
    private BufferedImage currentImage = null;
    // weapon's orientation
    private boolean isRight;

    public Ranged() {
        this.strenght = 30;
        this.durability = 50;
    }

    @Override
    public void crit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void destory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void damageEffect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Armory getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle toRectangle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BufferedImage getCurrentImage(boolean attackRight) {
        if (isRight) {
            currentImage = StaticValue.ranged_effects.get(0);
        } else {
            currentImage = StaticValue.ranged_effects.get(1);
        }
        return currentImage;
    }

    public void setX(int x) {
        this.effectsX = x;
    }

    public void setY(int y) {
        this.effectsY = y;
    }

    @Override
    public void setBackground(Background currentBackground) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAttacked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setX(int x, boolean faceRight) {
        // TODO Auto-generated method stub
        
    }

    
}
