package Weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Frame.Background;

public interface Weapon {
    
    void crit();

    void destory();

    void damageEffect();

    Armory getType();

    Rectangle toRectangle();

    BufferedImage getCurrentImage(boolean attackRight);

    void setBackground(Background currentBackground);

    boolean isAttacked();

    int getX();

    void setX(int x, boolean faceRight);

    int getStrength();

    int getDurability();

    void setDurability(int cost) throws Exception;
}
