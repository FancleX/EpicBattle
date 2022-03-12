package Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Frame.Background;
import Frame.StaticValue;
import Weapon.*;
import java.awt.image.BufferedImage;

public abstract class Character {
    
    protected int strenght;
    protected int hp;
    protected int mana;
    protected int x;
    protected int y;
    // weapon engaged
    private Weapon weapon;
    // background
    private Background background;
    // current weapon effects
    private BufferedImage currentEffects;
    // attacking status
    private boolean isAttacking = false;
    // current weapon
    private Armory currentWeapons = Armory.MELEE;
    // list of weapons
    private List<Weapon> availableWeapons = new ArrayList<>();
    // character orientaion
    protected boolean faceRight;
    // damage caused
    private int damage;

    //initialize weapons
    public Character() {
        availableWeapons.add(new Melee());
        availableWeapons.add(new Ranged());
        availableWeapons.add(new Magic());
    }

 
    public void attack(boolean attackRight) {
        isAttacking = true;
        switch (currentWeapons) {
            case MELEE:
                currentEffects = availableWeapons.get(0).getCurrentImage(attackRight);
                break;
            case RANGED:
                currentEffects = availableWeapons.get(1).getCurrentImage(attackRight);
                break;
            case MAGIC:
                currentEffects = availableWeapons.get(2).getCurrentImage(attackRight);
                break;
        }
        Random rd = new Random();
        damage = rd.nextInt(strenght);
    }

    public void stopAttacking() {
        isAttacking = false;
        currentEffects = null;
    }

    public void hurt(int damage) {
        if (hp > 0) {
            hp -= damage;
        } else {
            hp = 0;
        }
    }

    public void manaCost(int mana) {
        if (this.mana > 0) {
            this.mana -= mana;
        } else {
            this.mana = 0;
        }
    }

    // track effects position
    // public void setEffectPosition() {
    //     switch (currentWeapons) {
    //         case MELEE:
    //             ((Melee) availableWeapons.get(0)).setX(x, faceRight);
    //             ((Melee) availableWeapons.get(0)).setY(y);
    //             break;
    //         case RANGED:
    //             ((Ranged) availableWeapons.get(1)).setX(x);
    //             ((Ranged) availableWeapons.get(1)).setY(y);
    //             break;
    //         case MAGIC:
    //             ((Magic) availableWeapons.get(2)).setX(x);
    //             ((Magic) availableWeapons.get(2)).setY(y);
    //             break;     
    //     }
    // }

    public BufferedImage getCurrentEffects() {
        return currentEffects;
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public Armory getCurrentWeapon() {
        return currentWeapons;
    }

    public List<Weapon> getAvailablWeapons() {
        return availableWeapons;
    }

    public int getDamage() {
        return damage;
    }

}
