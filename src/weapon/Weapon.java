/**
 * @teamMemebers Zhicun Chen, Yiqian Huang 
 */

package weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import character.Enemy;
import frame.Background;

/**
 * Weapon interface defines the general methods for each different weapon.
 */
public interface Weapon {

    /**
     * Weapons have a chance to deal double damage
     */
    void crit();

    /**
     * Get weapon's image.
     * 
     * @param attackRight if true, get right side image
     * @return weapon's image
     */
    BufferedImage getCurrentImage(boolean attackRight);

    /**
     * Set weapon's coordinate.
     * 
     * @param x x-axis coordinate
     * @param y y-axis coordinate
     */
    void setXY(int x, int y);

    /**
     * Set durability of the weapon.
     * 
     * @param cost durability deduction
     * @throws Exception if durability is reduced to zero throw exception
     */
    void setDurability(int cost) throws Exception;

    /**
     * Wrap the weapon by a rectangle.
     * 
     * @return rectangle
     */
    Rectangle toRectangle();

    /**
     * Get if the weapon hits an enemy
     * 
     * @return true if attacked
     */
    boolean isAttacked();

    /**
     * Set true if the weapon attacked an enemy
     * 
     * @param isAttacked true if attacked
     */
    void setIsAttacked(boolean isAttacked);

    /**
     * Set the background of the weapon
     * 
     * @param currentBackground current background
     */
    void setBackground(Background currentBackground);

    /**
     * Determine if user press "A" to attack
     * 
     * @param userAttack true if do so
     */
    void pressAttackKey(boolean userAttack);

    /**
     * Get the injured enemy.
     * 
     * @return injured enemy
     */
    Enemy getInjuredEnemy();

    /**
     * Get strength of the weapon after crit bonus.
     * 
     * @return strength of the weapon
     */
    int getStrength();

    /**
     * Get durability of the weapon.
     * 
     * @return durability of the weapon
     */
    int getDurability();

    /**
     * Get x-axis coordinate of the weapon.
     * 
     * @return x-axis coordinate
     */
    int getX();

    /**
     * Get y-axis coordinate of the weapon.
     * 
     * @return y-axis coordinate
     */
    int getY();
}
