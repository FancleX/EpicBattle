package Character;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import Frame.*;
import Weapon.Bullet;

public class Enemy implements Runnable {

    // coordinate of hero
    private int x;
    private int y;
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
    // speaking phases
    private String[] texts = {"You're a loser!", "Cringe guy!", "Darkness!"};
    // message type
    private int messageType;
    // bullet
    Bullet bullet;
    // count for controlling speed of bullet generation
    private int count = 0;

    public Enemy(int x, int y, boolean faceRight, Background background, int message) {
        this.strenght = 5;
        this.hp = 100;
        this.mana = 0;
        totalHP = 100;
        currentImage = StaticValue.enemyRunLeft.get(0);
        this.x = x;
        this.y = y;
        this.faceRight = faceRight;
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
            // orietation of enemy
            if (faceRight) {
                currentImage = StaticValue.enemyRunRight.get(iterator);
                x += 3;
            } else {
                currentImage = StaticValue.enemyRunLeft.get(iterator);
                x -= 3;
            }

            // determine if enemy can walk right and left
            boolean walkRight = true;
            boolean walkLeft = true;
            for (int i = 0; i < background.getObstacleList().size(); i++) {
                Obstacle obstacle = background.getObstacleList().get(i);
                // can't walk right
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    walkRight = false;
                }
                // can't walk left
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

            // bullet
            if (count % 100 == 0) {
                bullet = new Bullet(x, y, faceRight);
            }
            count++;

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

    public Bullet getBullet() {
        return bullet;
    }

    public boolean isFaceRight() {
        return faceRight;
    }
}
