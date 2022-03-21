/**
 * @teamMemebers Zhicun Chen, Yiqian Huang 
 */

package weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import character.Enemy;
import frame.Background;
import frame.StaticValue;

/**
 * Magic class is a magic weapon that includes the size, effects of the weapon,
 * and runs for damage detection.
 */
public class Magic implements Weapon, Runnable {

    // strength of the weapon
    private int strenght;
    // durability of the weapon
    private int durability;
    // coordinate of weapon effects
    private int effectsX;
    private int effectsY;
    // effects image
    private BufferedImage currentImage = null;
    // weapon's orientation
    private boolean isRight;
    // width
    private int width = 190;
    // height
    private int height = 298;
    // new thread
    private Thread thread = new Thread(this);
    // is attacked
    private boolean isAttacked = false;
    // background
    private Background background = new Background();
    // determine if user press "A" to attack
    private boolean userAttack = false;
    // current enemy
    private Enemy currentEnemy;
    // current strength
    private int currentStrength;
    // random
    private Random rd = new Random();

    /**
     * Instantiate a ranged weapon.
     * 
     * @param background current background of the character
     */
    public Magic(Background background) {
        this.strenght = 50;
        this.durability = 100;
        this.background = background;
        thread.start();
    }

    // weapon can crit up to 2 times damage, 33%
    @Override
    public void crit() {
        int type = rd.nextInt(3);
        if (type == 2) {
            currentStrength = strenght * 2;
        } else {
            currentStrength = strenght;
        }
    }

    @Override
    public BufferedImage getCurrentImage(boolean isRight) {
        this.isRight = isRight;
        currentImage = StaticValue.magicEffects.get(0);
        return currentImage;
    }

    // track coordinate
    @Override
    public void setXY(int x, int y) {
        if (isRight) {
            this.effectsX = x + 300;
        } else {
            this.effectsX = x - 450;
        }
        this.effectsY = y - 220;
    }

    @Override
    public void setDurability(int cost) throws Exception {
        if (durability - cost > 0) {
            durability -= cost;
        } else {
            durability = 0;
            throw new Exception();
        }

    }

    @Override
    public void run() {
        while (durability > 0) {
            // loop enemies to determine if weapon attacks an enemy
            for (int i = 0; i < background.getEnemies().size(); i++) {
                Enemy enemy = background.getEnemies().get(i);
                // System.err.println("x: " + effectsX + " y: " + effectsY);
                // System.err.println("enemyX: " + enemy.getX() + " enemyY: " + enemy.getY());
                // System.err.println("is attacked: " + isAttacked);
                // System.err.println("enemyhp: " + enemy.getHp());
                // System.err.println("durability: " + durability);
                if (toRectangle().intersects(enemy.toRectangle()) && userAttack) {
                    isAttacked = true;
                    currentEnemy = enemy;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Rectangle toRectangle() {
        return new Rectangle(effectsX - 5, effectsY - 5, width + 10, height + 10);
    }

    @Override
    public boolean isAttacked() {
        return isAttacked;
    }

    @Override
    public void setIsAttacked(boolean isAttacked) {
        this.isAttacked = isAttacked;
    }

    @Override
    public void setBackground(Background background) {
        this.background = background;
    }

    @Override
    public void pressAttackKey(boolean userAttack) {
        this.userAttack = userAttack;
    }

    @Override
    public Enemy getInjuredEnemy() {
        return currentEnemy;
    }

    @Override
    public int getStrength() {
        crit();
        return currentStrength;
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public int getX() {
        return effectsX;
    }

    @Override
    public int getY() {
        return effectsY;
    }
}
