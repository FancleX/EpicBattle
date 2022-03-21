/**
 * @authors Zhicun Chen, Yiqian Huang 
 */

package character;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Rectangle;

import frame.*;
import weapon.Bullet;

/**
 * Enemy class has enemy coordinates, size, actions.
 */
public class Enemy extends Character implements Runnable {

    // coordinate of hero
    private int x;
    private int y;
    // determine the moving direction of enemy, true: face to right, false: face to
    // left
    private boolean faceRight = true;
    // display current enemy pictures
    private BufferedImage currentImage;
    // determine background
    private Background background;
    // moving range
    private int maxRight = 700;
    private int maxLeft = 400;
    // strength of enemy
    // private int strenght;
    private int currentStrength;
    // hp of enemy
    private int hp;
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
    // // total hp
    // private int totalHP;
    // dialog
    private BufferedImage dialog = StaticValue.enemyDialog;
    // speaking phases
    private String[] texts = { "You're a loser!", "Cringe guy!", "Darkness!" };
    // message type
    private int messageType;
    // bullet
    Bullet bullet;
    // count for controlling speed of bullet generation
    private int count = 0;
    // determine strength for attacking
    Random rd = new Random();

    /**
     * Instantiate an enemy.
     * 
     * @param x          x-axis coordiante
     * @param y          y-axis coordinate
     * @param faceRight  true if enemy faces to right
     * @param background background of the enemy
     * @param message    type of message that enemy speaks
     */
    public Enemy(int x, int y, boolean faceRight, Background background, int message) {
        super(null, 100, 1);
        // this.strenght = 1;
        this.hp = 100;
        // totalHP = 100;
        currentImage = StaticValue.enemyRunLeft.get(0);
        this.x = x;
        this.y = y;
        this.faceRight = faceRight;
        this.background = background;
        this.messageType = message;
        thread.start();
    }

    /**
     * Enemy attacks from the back of the hero will deal 3 times damage.
     * 
     * @param faceRight true if face right
     */
    @Override
    public void attack(boolean faceRight) {
        int damage = rd.nextInt(getStrength()) + 1;
        currentStrength = damage;
    }

    // death
    @Override
    public void death() {
        currentImage = StaticValue.enemyDeath.get(0);
    }

    // enemy hurted
    @Override
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
            // loop animation
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
                if (obstacle.toRectangle().intersects(toRectangle())
                        && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    walkRight = false;
                }
                // can't walk left
                if (obstacle.toRectangle().intersects(toRectangle())
                        && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
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

            // bullet generation
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

    /**
     * Get current strength of enemy.
     * 
     * @return current strength
     */
    public int getCurrentStrength() {
        attack(faceRight);
        return currentStrength;
    }

    /**
     * Get hp of the enemy.
     * 
     * @return hp of the enemy
     */
    public int getHp() {
        return hp;
    }

    /**
     * Set hp of the enemy.
     * 
     * @param hp hp of the enemy
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Wrap the enemy by a rectangle.
     * 
     * @return rectangle
     */
    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Get current image of the enemy.
     * 
     * @return current image of the enemy
     */
    public BufferedImage getCurrentImage() {
        return currentImage;
    }

    /**
     * Get x-axis coordinate.
     * 
     * @return x-axis coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get y-axis coordinate.
     * 
     * @return y-axis coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Determine if the enemy is alive.
     * 
     * @return true if enemy is alive
     */
    public boolean isAlive() {
        if (hp <= 0) {
            return false;
        }
        return true;
    }

    /**
     * Get background of the enemy.
     * 
     * @return background of the enemy
     */
    public Background getBackground() {
        return background;
    }

    /**
     * Get hp bar image of the enemy.
     * 
     * @return hp bar image
     */
    public BufferedImage getHpBar() {
        return hpBar;
    }

    /**
     * Get the image of dialog box.
     * 
     * @return image of dialog box
     */
    public BufferedImage getDialog() {
        return dialog;
    }

    /**
     * Get bullet.
     * 
     * @return bullet
     */
    public Bullet getBullet() {
        return bullet;
    }

    /**
     * Get true if the enemy faces right
     * 
     * @return true if the enemy faces right
     */
    public boolean isFaceRight() {
        return faceRight;
    }
}
