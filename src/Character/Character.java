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
        int damage = rd.nextInt(strenght);
        hp -= damage;
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



}
