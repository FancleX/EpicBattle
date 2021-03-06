/**
 * @authors Zhicun Chen, Yiqian Huang  
 */

package character;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

import frame.*;
import weapon.*;

/**
 * Hero class has hero coordinates, size, actions.
 */
public class Hero extends Character implements Runnable {

    // mana
    private int mana;
    // coordinate of hero
    private int x;
    private int y;
    // current status
    /*
     * status can be
     * "melee_weapon_right": hero holds melee weapon and towards right
     * "melee_weapon_left": hero holds melee weapon and towards left
     * "ranged_weapon_right": hero holds ranged weapon and towards right
     * "ranged_weapon_left": hero holds ranged weapon and towards left
     * "magic_weapon_right": hero holds magic weapon and towards right
     * "magic_weapon_left": hero holds magic weapon and towards left
     * "jump" : is jumping right now
     * "jump_left": jump to left
     * "jump_right": jump to right
     * "move_left": move to left
     * "move_right": move to right
     * "stop_left": stop and towards left
     * "stop_right": stop and towards right
     */
    private String status;
    // the picture corresponding to the current status
    private BufferedImage image = StaticValue.meleeWeaponRight;
    // get obstacle information
    private Background background = new Background();
    // hero UI
    private BufferedImage ui = null;
    // create thread to drive hero
    private Thread thread = new Thread(this);
    // moving speed on x-axis
    private int xSpeed;
    // jumping speed on y-axis
    private int ySpeed;
    // define an index to get image of moving images
    private int index;
    // jumping time
    private int jumpingTime;
    // height
    private int height = 60;
    // width
    private int width = 40;
    // current hp
    private int currentHp;
    // current mana
    private int currentMana;
    // hero is alive
    private boolean isAlive = true;
    // hero orientation
    private boolean faceRight;
    // current weapon
    private Armory currentWeapon;
    // weapon tag list
    private List<Armory> weaponList = new ArrayList<>();
    // weapon list
    private List<Weapon> weapons = new ArrayList<>();
    // hero is attcking
    private boolean isAttacking = false;
    // weapon effects of current weapon
    private BufferedImage currentWeaponEffects = null;
    // if hero runs out of weapon then fail the game
    private boolean isNoWeapon = false;

    /**
     * Instantiate a hero.
     * 
     * @param name     name of the hero
     * @param strenght strength of the hero
     * @param hp       hp of the hero
     * @param mana     mana of the hero
     */
    public Hero(String name, int strenght, int hp, int mana) {
        super(name, hp, strenght);
        this.mana = mana;
        this.currentHp = hp;
        this.currentMana = mana;

        image = StaticValue.meleeWeaponRight;
        this.status = "melee_weapon_right";
        // initialize ui
        ui = StaticValue.heroUI;
        // initialize weapon system
        weaponList.add(Armory.MELEE);
        weaponList.add(Armory.RANGED);
        weaponList.add(Armory.MAGIC);
        weapons.add(new Melee(background));
        weapons.add(new Ranged(background));
        weapons.add(new Magic(background));
        currentWeapon = Armory.MELEE;

        thread.start();
    }

    /**
     * Hero moves to left.
     */
    public void moveLeft() {
        // change speed to nagative
        xSpeed = -10;
        // if the hero is jumping
        if (status != "jump") {
            status = "move_left";
        } else {
            status = "jump_left";
        }
    }

    /**
     * Hero moves to right.
     */
    public void moveRight() {
        xSpeed = 10;
        if (status != "jump") {
            status = "move_right";
        } else {
            status = "jump_right";
        }
    }

    /**
     * Hero stops at left.
     */
    public void stopLeft() {
        // change xSpeed to zero
        xSpeed = 0;
        // if is jumping
        if (status != "jump") {
            status = "stop_left";
        } else {
            status = "jump_left";
        }
    }

    /**
     * Hero stops at right.
     */
    public void stopRight() {
        xSpeed = 0;
        if (status != "jump") {
            status = "stop_right";
        } else {
            status = "jump_right";
        }
    }

    /**
     * Hero jumps up.
     */
    public void jump() {
        // if is jumping
        if (status != "jump") {
            // if jump to left
            if (status.contains("left")) {
                status = "jump_left";
            } else {
                status = "jump_right";
            }
            ySpeed = -12;
            jumpingTime = 10;
            int height = ySpeed * jumpingTime;
            y += height;
        }
    }

    /**
     * Hero falls down.
     */
    public void fall() {
        // detect if is on ground and stop falling on the ground
        if (status.contains("left")) {
            status = "jump_left";
        } else {
            status = "jump_right";
        }
        ySpeed = 7;
        // change position when moving on jumping
        y += ySpeed;
    }

    // death
    @Override
    public void death() {
        switch (currentWeapon) {
            case MELEE:
                image = StaticValue.heroDeath.get(0);
                break;
            case RANGED:
                image = StaticValue.heroDeath.get(1);
                break;
            case MAGIC:
                image = StaticValue.heroDeath.get(2);
                break;
        }
        isAlive = false;
    }

    /**
     * Hero orientation.
     * 
     * @return true if hero faces right
     */
    public boolean faceRight() {
        if (status.contains("right")) {
            faceRight = true;
            return faceRight;
        } else {
            faceRight = false;
            return faceRight;
        }
    }

    /**
     * Hero change weapon.
     */
    public void changeWeapon() {
        currentWeaponEffects = null;
        switch (currentWeapon) {
            case MELEE:
                if (weaponList.contains(Armory.RANGED)) {
                    currentWeapon = Armory.RANGED;
                } else if (weaponList.contains(Armory.MAGIC)) {
                    currentWeapon = Armory.MAGIC;
                }
                break;
            case RANGED:
                if (weaponList.contains(Armory.MAGIC)) {
                    currentWeapon = Armory.MAGIC;
                } else if (weaponList.contains(Armory.MELEE)) {
                    currentWeapon = Armory.MELEE;
                }
                break;
            case MAGIC:
                if (weaponList.contains(Armory.MELEE)) {
                    currentWeapon = Armory.MELEE;
                } else if (weaponList.contains(Armory.RANGED)) {
                    currentWeapon = Armory.RANGED;
                }
                break;
        }
    }

    // hero attack
    @Override
    public void attack(boolean isRight) {
        isAttacking = true;
        switch (currentWeapon) {
            case MELEE:
                currentWeaponEffects = weapons.get(0).getCurrentImage(isRight);
                weapons.get(0).pressAttackKey(true);
                break;
            case RANGED:
                currentWeaponEffects = weapons.get(1).getCurrentImage(isRight);
                weapons.get(1).pressAttackKey(true);
                break;
            case MAGIC:
                currentWeaponEffects = weapons.get(2).getCurrentImage(isRight);
                weapons.get(2).pressAttackKey(true);
                break;
        }
    }

    /**
     * Hero stops attacking.
     */
    public void stopAttacking() {
        isAttacking = false;
        currentWeaponEffects = null;
        switch (currentWeapon) {
            case MELEE:
                weapons.get(0).pressAttackKey(false);
                break;
            case RANGED:
                weapons.get(1).pressAttackKey(false);
                break;
            case MAGIC:
                weapons.get(2).pressAttackKey(false);
                break;
        }
    }

    // hero hurted
    @Override
    public void hurted(int damage) {
        if (currentHp - damage > 0) {
            currentHp -= damage;
        } else {
            currentHp = 0;
            death();
        }
    }

    /**
     * Get damage by weapon bonus.
     * 
     * @return damage
     */
    public int causedDamage() {
        int damage = 0;
        Weapon weapon = null;
        switch (currentWeapon) {
            case MELEE:
                weapon = weapons.get(0);
                // bonus damage from the weapon
                damage = getStrength() + weapon.getStrength();
                break;
            case RANGED:
                weapon = weapons.get(1);
                // bonus damage from the weapon
                // determine if the weapon missing the attack, 25% possibility
                int chance = weapon.getStrength();
                if (chance == 0) {
                    damage = 0;
                } else {
                    damage = getStrength() + chance;
                }
                break;
            case MAGIC:
                weapon = weapons.get(2);
                // bonus damage from the weapon
                damage = getStrength() + weapon.getStrength();
                break;
        }
        return damage;
    }

    /**
     * Track weapon position.
     */
    public void trackWeaponPosition() {
        switch (currentWeapon) {
            case MELEE:
                weapons.get(0).setXY(x, y);
                weapons.get(0).setBackground(background);
                break;
            case RANGED:
                weapons.get(1).setXY(x, y);
                weapons.get(1).setBackground(background);
                break;
            case MAGIC:
                weapons.get(2).setXY(x, y);
                weapons.get(2).setBackground(background);
                break;
        }
    }

    @Override
    public void run() {
        while (true) {
            // determine hero's action
            boolean isOnObstacle = false;
            boolean passLeft = true;
            boolean passRight = true;
            // iterate obstacles
            for (int i = 0; i < background.getObstacleList().size(); i++) {
                Obstacle obstacle = background.getObstacleList().get(i);
                // determine if hero is on obstacles
                // if (obstacle.toRectangle().intersects(toRectangle())) {
                // isOnObstacle = true;
                // }
                // System.err.println("obstacleY: " + obstacle.getY() + " obstacleType: " +
                // obstacle.getType());
                // System.err.println("heroY: " + this.y);
                // obstacle.getY() > this.y + 45 && obstacle.getY() < this.y + 55
                if ((obstacle.toRectangle().intersects(toRectangle()))
                        && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY() + obstacle.getWidth() + 15)
                        && (this.y <= obstacle.getY() - 3)) {
                    isOnObstacle = true;
                }
                // determine if hero can walk right head: 494 ground: 550 hero width: 70
                // System.err.println("obstacleY: " + obstacle.getY() + " type: " +
                // obstacle.getType());
                // System.err.println("heroY: " + y);
                // obstacle.getX() == this.x + 70
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle())
                        && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passRight = false;
                }
                // determine if hero can walk left
                // System.err.println("obstacleX: " + obstacle.getX());
                // System.err.println("heroX: " + this.x);
                // System.err.println("obstacleWidth: " + obstacle.getWidth() + " obstacleType:
                // " + obstacle.getType());
                // obstacle.getX() == this.x - obstacle.getWidth() - 30
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle())
                        && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passLeft = false;
                }
                // System.err.println(passRight);
                // determine if hero is on the bottom of the obstacle
                // if ((obstacle.getY() <= this.y - obstacle.getHeight() - 5 && obstacle.getY()
                // >= this.y - 5 ) && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY()
                // + obstacle.getWidth() + 15)) {
                // jumpingTime = 0;
                // }
            }

            // attack damage calculation
            if (!weaponList.isEmpty() && isAttacking) {
                // track weapon position
                trackWeaponPosition();
                int durabilityDeduction = 0;
                switch (currentWeapon) {
                    case MELEE:
                        Weapon melee = weapons.get(0);
                        if (melee.isAttacked()) {
                            Enemy enemy = melee.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            melee.setIsAttacked(false);
                        }
                        // reduce durability
                        durabilityDeduction = 1;
                        try {
                            // decreaes weapon durability
                            melee.setDurability(durabilityDeduction);
                        } catch (Exception e) {
                            changeWeapon();
                            // weapon durability down to 0, remove it
                            weaponList.remove(Armory.MELEE);
                            e.printStackTrace();
                        }
                        // System.err.println("durability: " + weapon.getDurability());
                        break;
                    case RANGED:
                        Weapon ranged = weapons.get(1);
                        // System.err.println("durability: " + ranged.getDurability());
                        if (ranged.isAttacked()) {
                            Enemy enemy = ranged.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            ranged.setIsAttacked(false);
                        }
                        // reduce durability
                        durabilityDeduction = 5;
                        try {
                            // decreaes weapon durability
                            ranged.setDurability(durabilityDeduction);
                        } catch (Exception e) {
                            changeWeapon();
                            // weapon durability down to 0, remove it
                            weaponList.remove(Armory.RANGED);
                            e.printStackTrace();
                        }
                        break;
                    case MAGIC:
                        Weapon magic = weapons.get(2);
                        if (magic.isAttacked()) {
                            Enemy enemy = magic.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            magic.setIsAttacked(false);
                        }
                        // reduce mana
                        currentMana -= 40;
                        // reduce durability
                        durabilityDeduction = 10;
                        if (currentMana <= 0 && weaponList.size() != 1) {
                            changeWeapon();
                        } else {
                            try {
                                // decreaes weapon durability
                                magic.setDurability(durabilityDeduction);
                            } catch (Exception e) {
                                changeWeapon();
                                // weapon durability down to 0, remove it
                                weaponList.remove(Armory.MAGIC);
                                e.printStackTrace();
                            }
                        }
                        break;
                }
            } else if (weaponList.isEmpty()) {
                isNoWeapon = true;
            }
            // recover mana
            if (currentMana < 100) {
                currentMana += 1;
            }

            // display the action of jumping
            // stand on the obstacle
            if (isOnObstacle && jumpingTime == 0) {
                if (status.contains("left")) {
                    // move to left
                    if (xSpeed != 0) {
                        status = "move_left";
                    } else {
                        // stop at left
                        status = "stop_left";
                    }
                } else {
                    // move to right
                    if (xSpeed != 0) {
                        status = "move_right";
                    } else {
                        // stop at right
                        status = "stop_right";
                    }
                }
            } else {
                // hero is jumping and need to fall down
                if (jumpingTime != 0) {
                    jumpingTime--;
                } else {
                    // hero jumps to the most top
                    fall();
                }
            }

            // if hero is moving
            if ((xSpeed < 0 && passLeft) || (xSpeed > 0 && passRight)) {
                x += xSpeed;
                // boundary detection, if the hero runs to the left and right of the window
                // left boundary
                if (x < 0) {
                    x = 0;
                }
                // right boundary
                else if (x > 750) {
                    x = 750;
                }
            }
            // avoid being stuck by obstacles
            else if (!passLeft) {
                y -= 3;
                x -= 3;
            } else if (!passRight) {
                y -= 3;
                x += 3;
            }

            // connect images with moving status
            // rotate the index from 0 to 1 to repeat displaying our moving images
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            // detect status and assign the images of the status
            if (currentWeapon.equals(Armory.MELEE)) {
                switch (status) {
                    // move to left
                    case "move_left":
                        image = StaticValue.heroRunLeftMelee.get(index);
                        break;
                    // move to right
                    case "move_right":
                        image = StaticValue.heroRunRightMelee.get(index);
                        break;
                    // stop and towards left
                    case "stop_left":
                        image = StaticValue.meleeWeaponLeft;
                        break;
                    // stop and towards right
                    case "stop_right":
                        image = StaticValue.meleeWeaponRight;
                        break;
                    // jump to left
                    case "jump_left":
                        image = StaticValue.jumpToLeftMelee;
                        break;
                    // jump to right
                    case "jump_right":
                        image = StaticValue.jumpToRightMelee;
                        break;
                }
            } else if (currentWeapon.equals(Armory.RANGED)) {
                switch (status) {
                    // move to left
                    case "move_left":
                        image = StaticValue.heroRunLeftRanged.get(index);
                        break;
                    // move to right
                    case "move_right":
                        image = StaticValue.heroRunRightRanged.get(index);
                        break;
                    // stop and towards left
                    case "stop_left":
                        image = StaticValue.rangedWeaponLeft;
                        break;
                    // stop and towards right
                    case "stop_right":
                        image = StaticValue.rangedWeaponRight;
                        break;
                    // jump to left
                    case "jump_left":
                        image = StaticValue.jumpToLeftRanged;
                        break;
                    // jump to right
                    case "jump_right":
                        image = StaticValue.jumpToRightRanged;
                        break;
                }
            } else if (currentWeapon.equals(Armory.MAGIC)) {
                switch (status) {
                    // move to left
                    case "move_left":
                        image = StaticValue.heroRunLeftMagic.get(index);
                        break;
                    // move to right
                    case "move_right":
                        image = StaticValue.heroRunRightMagic.get(index);
                        break;
                    // stop and towards left
                    case "stop_left":
                        image = StaticValue.magicWeaponLeft;
                        break;
                    // stop and towards right
                    case "stop_right":
                        image = StaticValue.magicWeaponRight;
                        break;
                    // jump to left
                    case "jump_left":
                        image = StaticValue.jumpToLeftMagic;
                        break;
                    // jump to right
                    case "jump_right":
                        image = StaticValue.jumpToRightMagic;
                        break;
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get mana of the hero.
     * 
     * @return mana
     */
    public int getMana() {
        return mana;
    }

    /**
     * Set mana of the hero.
     * 
     * @param mana mana
     */
    public void setMana(int mana) {
        this.mana = mana;
    }

    /**
     * Set x-axis coordinate.
     * 
     * @param x x-axis coordinate
     */
    public void setX(int x) {
        this.x = x;
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
     * Set y-axis coordinate.
     * 
     * @param y y-axis coordinate
     */
    public void setY(int y) {
        this.y = y;
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
     * Get image.
     * 
     * @return image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get background.
     * 
     * @return background
     */
    public Background getBackground() {
        return background;
    }

    /**
     * Set background.
     * 
     * @param background current background
     */
    public void setBackground(Background background) {
        this.background = background;
    }

    /**
     * Wrap hero by a rectangle.
     * 
     * @return rectangle
     */
    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    /**
     * Get ui image.
     * 
     * @return ui image
     */
    public BufferedImage getUI() {
        return ui;
    }

    /**
     * Get current hp.
     * 
     * @return current hp
     */
    public int getCurrentlHp() {
        return currentHp;
    }

    /**
     * Set current hp.
     * 
     * @param hp current hp
     */
    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    /**
     * Get current mana.
     * 
     * @return current mana
     */
    public int getCurrentMana() {
        return currentMana;
    }

    /**
     * Set current mana.
     * 
     * @param mana current mana
     */
    public void setCurrentMana(int mana) {
        this.currentMana = mana;
    }

    /**
     * Determine if hero is alive.
     * 
     * @return true if hero is alive
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Get weapon tag list.
     * 
     * @return weapon tag list
     */
    public List<Armory> getWeaponList() {
        return weaponList;
    }

    /**
     * Get weapon object list.
     * 
     * @return weapon object list
     */
    public List<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Determine if hero is attacking.
     * 
     * @return true if hero is attacking
     */
    public boolean isAttacking() {
        return isAttacking;
    }

    /**
     * Get current weapon effects.
     * 
     * @return current weapon effects
     */
    public BufferedImage getCurrentWeaponEffects() {
        return currentWeaponEffects;
    }

    /**
     * Determine if there is no weapon to use.
     * 
     * @return true if no weapon to use
     */
    public boolean isNoWeapon() {
        return isNoWeapon;
    }

    /**
     * Get current weapon tag.
     * 
     * @return current weapon tag
     */
    public Armory getCurrentWeapon() {
        return currentWeapon;
    }
}
