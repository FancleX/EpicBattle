package Character;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.awt.Rectangle;

import Frame.*;
import Weapon.Armory;
import Weapon.Magic;
import Weapon.Melee;
import Weapon.Ranged;
import Weapon.Weapon;


public class Hero implements Runnable{
    
    private String name;
    private int strenght;
    private int hp;
    private int mana;

    // coordinate of hero
    private int x;
    private int y;
    // current status
    /* status can be 
    "melee_weapon_right": hero holds melee weapon and towards right
    "melee_weapon_left": hero holds melee weapon and towards left
    "ranged_weapon_right": hero holds ranged weapon and towards right
    "ranged_weapon_left": hero holds ranged weapon and towards left
    "magic_weapon_right": hero holds magic weapon and towards right
    "magic_weapon_left": hero holds magic weapon and towards left
    "jump" : is jumping right now
    "jump_left": jump to left
    "jump_right": jump to right
    "move_left": move to left
    "move_right": move to right
    "stop_left": stop and towards left
    "stop_right": stop and towards right
    */
    private String status;
    // the picture corresponding to the current status
    private BufferedImage image = StaticValue.meleeWeaponRight;
    // get obstacle information
    private Background background = new Background();
    // hero UI
    private BufferedImage ui = null;
    // create thread to drive hero
    private Thread thread = null;
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

    public Hero(String name, int strenght, int hp, int mana) {
        this.name = name;
        this.strenght = strenght;
        this.hp = hp;
        this.mana = mana;
        this.currentHp = hp;
        this.currentMana = mana;

        image = StaticValue.meleeWeaponRight;
        this.status = "melee_weapon_right";
        ui = StaticValue.heroUI;
        weaponList.add(Armory.MELEE);
        weaponList.add(Armory.RANGED);
        weaponList.add(Armory.MAGIC);
        weapons.add(new Melee(background));
        weapons.add(new Ranged(background));
        weapons.add(new Magic(background));
        currentWeapon = Armory.MELEE;

        thread = new Thread(this);
        thread.start();
    }

    // hero moves to left
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

    // hero moves to right
    public void moveRight() {
        xSpeed = 10;
        if (status != "jump") {
            status = "move_right";
        } else {
            status = "jump_right";
        }
    }

    // hero stops at left
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

    // hero stops at right
    public void stopRight() {
        xSpeed = 0;
        if (status != "jump") {
            status = "stop_right";
        } else {
            status = "jump_right";
        }
    }

    // hero jumps up
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

    // hero falls down
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
    public void death() {
        image = StaticValue.heroDeath;
        isAlive = false;
    }

    // hero orientation
    public boolean faceRight() {
        if (status.contains("right")) {
            faceRight = true;
            return faceRight;
        } else {
            faceRight = false;
            return faceRight;
        }
    }

    // hero change weapon
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

    // hero stops attacking
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
    public void hurted(int damage) {
        if (hp - damage > 0) {
            hp -= damage;
        } else {
            hp = 0;
            death();
        }
    }

    // get damage by weapon bonus
    public int causedDamage() {
        int damage = 0;
        Weapon weapon = null;
        switch (currentWeapon) {
            case MELEE:
                weapon = weapons.get(0);
                // bonus damage from the weapon
                damage = this.strenght + weapon.getStrength();
                break;
            case RANGED:
                weapon = weapons.get(1);
                // bonus damage from the weapon
                damage = this.strenght + weapon.getStrength();
                break;
            case MAGIC:
                weapon = weapons.get(2);
                // bonus damage from the weapon
                damage = this.strenght + weapon.getStrength();
                break;
        }
        return damage;
    }

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
                //     isOnObstacle = true;
                // }
                // System.err.println("obstacleY: " + obstacle.getY() + " obstacleType: " + obstacle.getType());
                // System.err.println("heroY: " + this.y);
                // obstacle.getY() > this.y + 45 && obstacle.getY() < this.y + 55
                if ((obstacle.toRectangle().intersects(toRectangle())) && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY() + obstacle.getWidth() + 15)) {
                    isOnObstacle = true;
                }
                // determine if hero can walk right head: 494  ground: 550 hero width: 70
                // System.err.println("obstacleY: " + obstacle.getY() + " type: " + obstacle.getType());
                // System.err.println("heroY: " + y);
                // obstacle.getX() == this.x + 70
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passRight = false;
                }
                // determine if hero can walk left
                // System.err.println("obstacleX: " + obstacle.getX());
                // System.err.println("heroX: " + this.x);
                // System.err.println("obstacleWidth: " + obstacle.getWidth() + " obstacleType: " + obstacle.getType());
                // obstacle.getX() == this.x - obstacle.getWidth() - 30
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passLeft = false;
                }
                // System.err.println(passRight);
                // determine if hero is on the bottom of the obstacle
                // if ((obstacle.getY() <= this.y - obstacle.getHeight() - 5 && obstacle.getY() >= this.y - 5 ) && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY() + obstacle.getWidth() + 15)) {
                //     jumpingTime = 0;
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
                        // melee.setXY(x, y);
                        // melee.setBackground(background);
                        if (melee.isAttacked()) {
                            Enemy enemy = melee.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            melee.setIsAttacked(false);
                        }
                        // reduce durability
                        durabilityDeduction = 5;
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
                        // ranged.setXY(x, y);
                        // ranged.setBackground(background);
                        System.err.println("durability: " + ranged.getDurability());
                        if (ranged.isAttacked()) {
                            Enemy enemy = ranged.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            ranged.setIsAttacked(false);
                        }
                        // reduce durability
                        durabilityDeduction = 10;
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
                        // magic.setXY(x, y);
                        // magic.setBackground(background);
                        if (magic.isAttacked()) {
                            Enemy enemy = magic.getInjuredEnemy();
                            enemy.hurted(causedDamage());
                            // finish attacking
                            magic.setIsAttacked(false);
                        }
                        // reduce durability
                        durabilityDeduction = 0;
                        try {
                            // decreaes weapon durability
                            magic.setDurability(durabilityDeduction);
                        } catch (Exception e) {
                            changeWeapon();
                            // weapon durability down to 0, remove it 
                            weaponList.remove(Armory.MAGIC);
                            e.printStackTrace();
                        }
                        break;
                }
            } else if (weaponList.isEmpty()) {
                isNoWeapon = true;
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
                // TODO Auto-generated catch block
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

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;        
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public BufferedImage getUI() {
        return ui;
    }

    public String getName() {
        return name;
    }

    public int getCurrentlHp() {
        return currentHp;
    }

    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = mana;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public List<Armory> getWeaponList() {
        return weaponList;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public BufferedImage getCurrentWeaponEffects() {
        return currentWeaponEffects;
    }

    public boolean isNoWeapon() {
        return isNoWeapon;
    }

    public Armory getCurrentWeapon() {
        return currentWeapon;
    }
}
