package Character;
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
 
    public void attack(boolean attackRight) {
        switch (weapon.getType()) {
            case MELEE:
                if (attackRight) {
                    currentEffects = StaticValue.melee_effects.get(0);
                } else {
                    currentEffects = StaticValue.melee_effects.get(1);
                }
                break;
            case RANGED:
                if (attackRight) {
                    currentEffects = StaticValue.ranged_effects.get(0);
                } else {
                    currentEffects = StaticValue.ranged_effects.get(1);
                }
                break;
            case MAGIC:
                currentEffects = StaticValue.magic_effects.get(0);
                break;
        }
        Random rd = new Random();
        int damage = rd.nextInt(strenght);
        hp -= damage;
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




}
