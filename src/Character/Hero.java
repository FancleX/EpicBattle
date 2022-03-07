package Character;
public class Hero extends Character{
    
    private String name;
    private int strenght;
    private int hp;
    private int mana;

    public Hero(String name, int strenght, int hp, int mana) {
        super(strenght, hp, mana);
        this.name = name;
        this.strenght = strenght;
        this.hp = hp;
        this.mana = mana;
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

}
