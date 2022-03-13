package Weapon;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Character.Enemy;
import Frame.Background;
import Frame.StaticValue;

public class Magic implements Weapon{

    private int strenght;
    private int durability;
    // coordinate of weapon effects
    private int effectsX;
    private int effectsY;
    // effects image
    private BufferedImage currentImage = null;
    // weapon's orientation
    private boolean isRight;
    // background
    private Background background = new Background();

    public Magic(Background background) {
        this.strenght = 10;
        this.durability = 20;
        this.background = background;
    }

    @Override
    public void crit() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void destory() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void damageEffect() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Armory getType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rectangle toRectangle() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BufferedImage getCurrentImage(boolean attackRight) {
        currentImage = StaticValue.magic_effects.get(0);
        return currentImage;
    }

    public void setX(int x) {
        this.effectsX = x;
    }

    public void setY(int y) {
        this.effectsY = y;
    }

    @Override
    public void setBackground(Background currentBackground) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isAttacked() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getX() {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public void setXY(int x, int y, boolean faceRight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getStrength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDurability() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setDurability(int cost) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pressAttackKey(boolean b) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Enemy getInjuredEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
