package Weapon;

import java.awt.Rectangle;
import Frame.*;
import Character.*;
import java.awt.image.BufferedImage;

public class Melee implements Weapon {
    
    private int strenght;
    private int durability;
    // coordinate of weapon effects
    private int effectsX;
    private int effectsY;
    // effects image
    private BufferedImage currentImage = null;
    // weapon's orientation
    private boolean isRight;

    public Melee() {
        this.strenght = 2;
        this.durability = 100;
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
        return Armory.MELEE;
    }

    @Override
    public Rectangle toRectangle() {
        return null;
    }

    public BufferedImage getCurrentImage(boolean isRight) {
        this.isRight = isRight;
        if (isRight) {
            currentImage = StaticValue.melee_effects.get(0);
        } else {
            currentImage = StaticValue.melee_effects.get(1);
        }
        return currentImage;
    }
}
