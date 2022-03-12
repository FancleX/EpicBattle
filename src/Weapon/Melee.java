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
    // determine if user press "A" to attack
    private boolean userAttack = false;

    public Melee(Background background) {
        this.strenght = 2;
        this.durability = 100;
        this.background = background;
        thread.start();
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

    public void pressAttackKey(boolean userAttack) {
        this.userAttack = userAttack;
    }



    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < background.getEnemies().size(); i++) {
                Enemy enemy = background.getEnemies().get(i);
                System.err.println("x: " + effectsX);
                System.err.println("enemyX: " + enemy.getX());
                System.err.println("is attacked: " + isAttacked);
                System.err.println("enemyhp: " + enemy.getHp());
                // (effectsX <= enemy.getX() - 5 && effectsX >= enemy.getX() - 10)
                if (toRectangle().intersects(enemy.toRectangle()) && userAttack) {
                    isAttacked = true;
                    enemy.hurted(8);
                } else {
                    isAttacked = false;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

        }
        
    }


    @Override
    public int getStrength() {
        return strenght;
    }


    @Override
    public int getDurability() {
        return durability;
    }


    @Override
    public void setDurability(int cost) throws Exception {
        if (durability - cost >= 0) {
            durability -= cost;
        } else {
            durability = 0;
            throw new Exception();
        }

    }

    

}
