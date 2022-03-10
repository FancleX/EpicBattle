package Character;
import java.util.Random;

public abstract class Character {
    
    protected int strenght;
    protected int hp;
    protected int mana;

    public int attack() {
        Random rd = new Random();
        int damage = rd.nextInt(strenght);
        return damage;
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







}
