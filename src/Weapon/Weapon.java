package Weapon;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Character.Enemy;
import Frame.Background;
import Frame.GameFrame;

public interface Weapon {
    
    void crit();

    BufferedImage getCurrentImage(boolean attackRight);

    void setXY(int x, int y);

    void setDurability(int cost) throws Exception;

    Rectangle toRectangle();

    boolean isAttacked();

    void setIsAttacked(boolean isAttacked);

    void setBackground(Background currentBackground);

    void pressAttackKey(boolean userAttack);

    Enemy getInjuredEnemy();

    int getStrength();

    int getDurability();

    int getX();

    int getY();

}
