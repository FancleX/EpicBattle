package Weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface Weapon {
    
    void crit();

    void destory();

    void damageEffect();

    Armory getType();

    Rectangle toRectangle();

    BufferedImage getCurrentImage(boolean attackRight);
}
