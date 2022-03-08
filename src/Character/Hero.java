package Character;

import java.awt.image.BufferedImage;

import Frame.*;


public class Hero extends Character implements Runnable{
    
    private String name;
    private int strenght;
    private int hp;
    private int mana;

    // coordinate of hero
    private int x;
    private int y;
    // current status
    private String status;
    // the picture corresponding to the current state
    private BufferedImage image = null;
    // get obstacle information
    private Background background = new Background();
    // create thread to drive hero
    private Thread thread = null;

    public Hero(String name, int strenght, int hp, int mana) {
        super(strenght, hp, mana);
        this.name = name;
        this.strenght = strenght;
        this.hp = hp;
        this.mana = mana;

        image = StaticValue.melee_weapon_right;
        this.status = "melee_weapon_right";
        thread = new Thread(this);
        thread.start();
    }

  
    @Override
    public void run() {
        // TODO Auto-generated method stub
        
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

}
