package Weapon;

import java.awt.Rectangle;
import Frame.*;
import Character.*;
import java.awt.image.BufferedImage;

public class Melee implements Weapon, Runnable {
    
    private int strenght;
    private int durability;
    // coordinate of weapon effects
    private int effectsX;
    private int effectsY;
    // effects image
    private BufferedImage currentImage = null;
    // weapon's orientation
    private boolean isRight;
    // width
    private int width = 41;
    // height
    private int height = 62;
    // new thread
    private Thread thread = new Thread(this);
    // is attacked
    private boolean isAttacked = false;
    // background
    private Background background = new Background();

    public Melee() {
        this.strenght = 2;
        this.durability = 100;
        // thread.start();
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
        return new Rectangle(effectsX, effectsY, width, height);
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

    public void setX(int x, boolean isRight) {
        if (isRight) {
            this.effectsX = x + 35;
        } else {
            this.effectsX = x - 30;
        }
    }

    public void setY(int y) {
        this.effectsY = y - 10;
    }

    public int getX() {
        return effectsX;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Background getBackground() {
        return background;
    }



    @Override
    public void run() {
        // while (true) {
        //     for (int i = 0; i < background.getEnemies().size(); i++) {
        //         Enemy enemy = background.getEnemies().get(i);
        //         System.err.println("is attacked: " + toRectangle().intersects(enemy.toRectangle()));
        //         if (toRectangle().intersects(enemy.toRectangle())) {
        //             isAttacked = true;
        //         } else {
        //             isAttacked = false;
        //         }
        //     }
            

        // }
        
    }


}
