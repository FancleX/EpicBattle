package Character;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;
import java.awt.Rectangle;

import Frame.*;

public class Enemy extends Character implements Runnable{

    // coordinate of hero
    private int x;
    private int y;
    // type of enemy
    private int type;
    // determine the moving direction of enemy, true: face to right, false: face to left
    private boolean face_right = true;
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
    public Enemy(int x, int y, boolean face_right, int type, Background background) {
        switch (type) {
            case 0: 
                super.strenght = 5;
                super.hp = 100;
                super.mana = 0;
                currentImage = StaticValue.enemy_run_left_melee.get(0);
                break;
            case 1:
                super.strenght = 7;
                super.hp = 50;
                super.mana = 0;
                currentImage = StaticValue.enemy_run_left_ranged.get(0);
                break;
            case 2:
                super.strenght = 10;
                super.hp = 20;
                super.mana = Integer.MAX_VALUE;
                currentImage = StaticValue.enemy_run_left_magic.get(0);
                break;
        }
        this.x = x;
        this.y = y;
        this.face_right = face_right;
        this.type = type;
        this.background = background;
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            iterator = iterator == 0 ? 1 : 0;
            switch (type) {
                case 0:
                    if (face_right) {
                        currentImage = StaticValue.enemy_run_right_melee.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemy_run_left_melee.get(iterator);
                        x -= 3;
                    }
                    break;
                case 1:
                    if (face_right) {
                        currentImage = StaticValue.enemy_run_right_ranged.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemy_run_left_ranged.get(iterator);
                        x -= 3;
                    }
                    break;
                case 2:
                    if (face_right) {
                        currentImage = StaticValue.enemy_run_right_magic.get(iterator);
                        x += 3;
                    } else {
                        currentImage = StaticValue.enemy_run_left_magic.get(iterator);
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
            if ((face_right && (!walkRight)) || x > maxRight) {
                face_right = false;
            }
            // if can't walk left or reach left boundary
            else if (((!face_right) && (!walkLeft)) || x < maxLeft) {
                face_right = true;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }        
    }

    // death
    public void death() {
        currentImage = StaticValue.enemy_death;
        background.getEnemies().remove(this);
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
}
