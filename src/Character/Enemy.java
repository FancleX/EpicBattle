package Character;

import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.List;

import Frame.Background;
import Frame.StaticValue;

public class Enemy extends Character implements Runnable{

    // coordinate of hero
    private int x;
    private int y;
    // type of enemy
    private int type;
    // determine the moving direction of enemy, true: move to right, false: move to left
    private boolean move_right = true;
    // display current enemy pictures
    private BufferedImage currentImage;
    // determine background
    private Background background;
    // moving range
    private int maxRight = 30;
    private int maxLeft = 40;
    // strength of enemy
    private int strenght;
    // hp of enemy
    private int hp;
    // mana of enemy
    private int mana;
    // create a thread
    private Thread thread = new Thread(this);

    /* 
        enemy with melee info:
        type = 0
        strength = 5
        hp = 100
        mana = 0

        enemy with ranged weapon info:
        type = 1
        strength = 7
        hp = 50
        mana = 0

        enemy with magic weapon info:
        type = 2
        strength = 10
        hp = 20
        mana = Infinite
    */
    public Enemy(int x, int y, boolean move_right, int type, Background background) {
        switch (type) {
            case 0: 
                super.strenght = 5;
                super.hp = 100;
                super.mana = 0;
                currentImage = StaticValue.enemy_run_left_melee.get(0);
                break;
            case 1:
                super.strenght = 7;
                super.hp = 50;
                super.mana = 0;
                currentImage = StaticValue.enemy_run_left_ranged.get(0);
                break;
            case 2:
                super.strenght = 10;
                super.hp = 20;
                super.mana = Integer.MAX_VALUE;
                currentImage = StaticValue.enemy_run_left_magic.get(0);
                break;
        }
        this.x = x;
        this.y = y;
        this.move_right = move_right;
        this.type = type;
        this.background = background;
        thread.start();
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

    @Override
    public void run() {
        while (true) {
            
        }
        
    }
}
