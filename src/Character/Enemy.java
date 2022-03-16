package Character;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;
import java.util.Random;
import java.awt.Rectangle;

import Frame.*;

public class Enemy implements Runnable{

    // coordinate of hero
    private int x;
    private int y;
    // type of enemy
    private int type;
    // determine the moving direction of enemy, true: face to right, false: face to left
    private boolean faceRight = true;
    // display current enemy pictures
    private BufferedImage currentImage;
    // determine background
    private Background background;
    // moving range
    private int maxRight = 700;
    private int maxLeft = 400;
    // strength of enemy
    private int strenght;
    // hp of enemy
    private int hp;
    // mana of enemy
    private int mana;
    // create a thread
    private Thread thread = new Thread(this);
    // iterate images
    private int iterator = 0;
    // height
    private int height = 60;
    // width
    private int width = 40;
    // hp bar
    private BufferedImage hpBar = StaticValue.enemyHP;
    // total hp
    private int totalHP;
    // dialog
    private BufferedImage dialog = StaticValue.enemyDialog;
    // determine if the enemy is first released
    private boolean firstAppearance = true;
    // speaking phases
    private String[] texts = {"You're a loser!", "Cringe guy!", "Darkness!"};
    // message type
    private int messageType;

    /* 
        enemy with melee info:
        type = 0
        strength = 5
        hp = 100
        mana = 0

        enemy with ranged weapon info:
        type = 1
        strength = 7
        hp = 50
        mana = 0

        enemy with magic weapon info:
        type = 2
        strength = 10
        hp = 20
        mana = Infinite
    */
    public Enemy(int x, int y, boolean faceRight, int type, Background background, int message) {
        switch (type) {
            case 0: 
                this.strenght = 5;
                this.hp = 100;
                this.mana = 0;
                totalHP = 100;
                currentImage = StaticValue.enemyRunLeftMelee.get(0);
                break;
            case 1:
                this.strenght = 7;
                this.strenght = 50;
                this.mana = 0;
                currentImage = StaticValue.enemyRunLeftRanged.get(0);
                break;
            case 2:
                this.strenght = 10;
                this.hp = 20;
                this.mana = Integer.MAX_VALUE;
                currentImage = StaticValue.enemyRunLeftMagic.get(0);
                break;
        }
        this.x = x;
        this.y = y;
        this.faceRight = faceRight;
        this.type = type;
        this.background = background;
        this.messageType = message;
        thread.start();
    }

     // death
     public void death() {
        currentImage = StaticValue.enemyDeath.get(0);
    }

    // enemy hurted
    public void hurted(int damage) {
        if (hp - damage > 0) {
            hp -= damage;
        } else {
            hp = 0;
            death();
        }
    }

    // speak 
    public String speak() {
        return texts[messageType];
    }


    @Override
    public void run() {
        while (isAlive()) {
            iterator = iterator == 0 ? 1 : 0;
            switch (type) {
                case 0:
                    if (faceRight) {
                        currentImage = StaticValue.enemyRunRightMelee.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemyRunLeftMelee.get(iterator);
                        x -= 3;
                    }
                    break;
                case 1:
                    if (faceRight) {
                        currentImage = StaticValue.enemyRunRightRanged.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemyRunLeftRanged.get(iterator);
                        x -= 3;
                    }
                    break;
                case 2:
                    if (faceRight) {
                        currentImage = StaticValue.enemyRunRightMagic.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemyRunLeftMagic.get(iterator);
                        x -= 3;
                    }
                    break;
            }

            // determine if enemy can walk right and left
            boolean walkRight = true;
            boolean walkLeft = true;
            for (int i = 0; i < background.getObstacleList().size(); i++) {
                Obstacle obstacle = background.getObstacleList().get(i);
                // if can walk right
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    walkRight = false;
                }
                // if can walk left
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    walkLeft = false;
                }
            }

            // change path if meet an obstacle
            // if can not walk right or reach right boundary
            if ((faceRight && (!walkRight)) || x > maxRight) {
                faceRight = false;
            }
            // if can't walk left or reach left boundary
            else if (((!faceRight) && (!walkLeft)) || x < maxLeft) {
                faceRight = true;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }

   
    public int getStrength() {
        return strenght;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }

    public Background getBackground() {
        return background;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getHpBar() {
        return hpBar;
    }

    public int totalHP() {
        return totalHP;
    }

    public BufferedImage getDialog() {
        return dialog;
    }

    public boolean isFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(boolean firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

}
