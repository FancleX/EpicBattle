package Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Character.Enemy;
import Character.Hero;
import music.Music;

public class GameFrame extends JFrame implements KeyListener, Runnable {

    // store background
    private List<Background> allBackground = new ArrayList<>();
    // current background
    private Background currentBackground = new Background();
    // double cach
    private Image offScreenImage = null;
    // create a hero
    private Hero hero;
    // create a thread to do hero action
    private Thread thread = new Thread(this);
    // limit jumping gap
    private long firstJump = 0;
    private long secondJump = 1500;
    // limit attack gap
    private long firstAttack = 0;
    private long secondAttack = 200;
    // use for dialog drawing
    private int count = 0;
    // use for enemy death animation
    private int count1 = 0;
    // final x y
    private int finalX;
    private int finalY;
    // last scence
    private Background lastScence;

    public GameFrame() {
        // set window's size
        this.setSize(800, 600);
        // window displays in the center
        this.setLocationRelativeTo(null);
        // set visibility
        this.setVisible(true);
        // set close
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set window unresizable
        this.setResizable(false);
        // add keyboard listener
        this.addKeyListener(this);
        // set title
        this.setTitle("Epic Battle");
        // initialize static value
        StaticValue.initializer();
        // initialize hero with a specific coorindate
        hero = new Hero("Jack", 10, 100, 100);
        hero.setX(20);
        hero.setY(480);
        // initialize all scences
        for (int i = 1; i <= 3; i++) {
            allBackground.add(new Background(i, i == 3 ? true : false));
        }
        // set first scence to current scence
        currentBackground = allBackground.get(0);
        hero.setBackground(currentBackground);
        // sketch background
        repaint();
        // show tips
        JOptionPane.showMessageDialog(this, "Tips: Grean bar is your current weapon's durability, \n red bar is your current hp, \n blue bar is your current mana. \n"
            + "Weapon will lose if the durability drop to zero. \n If you run out of weapons, you fail the battle!");  
        // start thread
        thread.start();
        // add music
        new Music();
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = createImage(800, 600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0, 0, 800, 600);

        // draw background
        graphics.drawImage(currentBackground.getBackgroundImage(), 0, 0, this);

        // draw obstacles and ground
        for (Obstacle obstacle : currentBackground.getObstacleList()) {
            graphics.drawImage(obstacle.getRenderedImage(), obstacle.getX(), obstacle.getY(), this);
        }

        if (!(currentBackground.getIsFinal())) {
            // draw teleporter
            graphics.drawImage(currentBackground.getTeleporter(), currentBackground.getTeleporterX(), currentBackground.getTeleporterY(), this);
        } else {
            // draw end teleporter
            graphics.drawImage(currentBackground.getEnd(), 620, 445, this);
        }

        // draw hero
        graphics.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);

        // draw hero's info
        graphics.drawImage(hero.getUI(), 20, 40, this);
        graphics.setFont(new Font("TimesRoman", Font.BOLD, 27));
        graphics.setColor(Color.GRAY);
        graphics.drawString(hero.getName(), 165, 68);
        // draw hp
        graphics.setColor(Color.RED);
        // hero.setCurrentHp(73);
        graphics.fillRect(110, 76, (int) ((float) hero.getCurrentlHp() / hero.getHp() * 165), 5);
        // draw mana
        graphics.setColor(Color.BLUE);
        graphics.fillRect(110, 88, (int) ((float) hero.getCurrentMana() / hero.getMana() * 165), 5);
        // draw weapon durability
        switch (hero.getCurrentWeapon()) {
            case MELEE:
                graphics.setColor(Color.GREEN);
                graphics.fillRoundRect(106, 54, (int) ((float) hero.getWeapons().get(0).getDurability() / 100 * 26), 13, 17, 17);
                break;
            case RANGED:
                graphics.setColor(Color.GREEN);
                graphics.fillRoundRect(106, 54, (int) ((float) hero.getWeapons().get(1).getDurability() / 70 * 26), 13, 17, 17);
                break;
            case MAGIC:
                graphics.setColor(Color.GREEN);
                graphics.fillRoundRect(106, 54, (int) ((float) hero.getWeapons().get(2).getDurability() / 100 * 26), 13, 17, 17);
                break;   
        }
        
        // draw attack effects
        switch (hero.getCurrentWeapon()) {
            case MELEE:
                if (hero.faceRight()) {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() + 35, hero.getY() - 10, this);
                } else {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() - 30, hero.getY() - 10, this);
                }
                
                break;
            case RANGED:
                if (hero.faceRight()) {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() + 50, hero.getY(), this);
                } else {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() - 300, hero.getY(), this);
                }
                break;
            case MAGIC:
                if (hero.faceRight()) {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() + 300, hero.getY() - 220, this);
                } else {
                    graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() - 450, hero.getY() - 220, this);
                }
                break;
        }

        // if background changed, reset all counter
        if (lastScence != currentBackground) {
            count = 0;
            count1 = 0;
            lastScence = currentBackground;
        }

        // draw enemies
        Iterator<Enemy> iterator = currentBackground.getEnemies().iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            // determine if enemy is died
            if (!enemy.isAlive()) {
                if (count1 == 0) {
                    finalX = enemy.getX();
                    finalY = enemy.getY();
                }
                if (count1 < 14) {
                    graphics.drawImage(StaticValue.enemyDeath.get(count1), finalX, finalY - 30, this);
                    count1++;
                } else {
                    iterator.remove();
                }
            } else {
                // draw enemy phrases
                if (count < 20) {
                    // draw enemy speaking dialog
                    graphics.drawImage(enemy.getDialog(), enemy.getX() - 20, enemy.getY() - 75, this);
                    // draw message
                    graphics.setFont(new Font("TimesRoman", Font.BOLD, 11));
                    graphics.setColor(Color.ORANGE);
                    graphics.drawString(enemy.speak(), enemy.getX() - 10, enemy.getY() - 40);
                    count++;
                }                                                                                
                // draw enemy hp bar
                graphics.drawImage(enemy.getHpBar(), enemy.getX() - 10, enemy.getY() - 20, this);
                graphics.setColor(Color.RED);
                graphics.fillRect(enemy.getX() - 7, enemy.getY() - 14, (int) ((float) enemy.getHp() / enemy.totalHP() * 55), 8);
                
                // draw enemy itself
                graphics.drawImage(enemy.getCurrentImage(), enemy.getX(), enemy.getY(), this);
            }

        }

        // connect offscreen image to window
        g.drawImage(offScreenImage, 0, 0, this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // if press "->", then move right
        if (e.getKeyCode() == 39) {
            hero.moveRight();
        }

        // if press "<-", then move left
        if (e.getKeyCode() == 37) {
            hero.moveLeft();
        }

        // if press "↑", then jump
        if (e.getKeyCode() == 38) {
            if (secondJump - firstJump >= 1500) {
                firstJump = System.currentTimeMillis();
                hero.jump();
            }
        }
        secondJump = System.currentTimeMillis();

        // press "A" to attack
        if (e.getKeyCode() == 65) {
            if (secondAttack - firstAttack >= 200) {
                firstAttack = System.currentTimeMillis();
                hero.attack(hero.faceRight());
            }
        }
        secondAttack = System.currentTimeMillis();

        // press "Q" to change weapon
        if (e.getKeyCode() == 81) {
            hero.changeWeapon();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // if release "->", then stop right
        if (e.getKeyCode() == 39) {
            hero.stopRight();
        }

        // if release "<-", then stop left
        if (e.getKeyCode() == 37) {
            hero.stopLeft();
        }

        // if release "A", then stop attacking
        if (e.getKeyCode() == 65) {
            hero.stopAttacking();
        }
        
    }

    @Override
    public void run() {
        while (true) {
            // refresh the window
            repaint();
            try {
                Thread.sleep(50);
                // determine if hero is alive, otherwise end the game
                if ((!hero.isAlive()) ||  hero.isNoWeapon()) {
                    JOptionPane.showMessageDialog(this, "You lost!");
                    System.exit(0);
                }
                // determine if the hero has entered the teleporter, if so, enter the next level
                int currentLevel = hero.getBackground().getCurrentScence();
                switch (currentLevel) {
                    // level 1
                    case 1:
                        // level 1 pass condition
                        if ((hero.getX() >= 740 && hero.getY() >= 460 && hero.getY() <= 550) && currentBackground.getEnemies().isEmpty()) {
                            currentBackground = allBackground.get(currentBackground.getCurrentScence());
                            // send the background to hero
                            hero.setBackground(currentBackground);
                            // reset hero postition
                            hero.setX(20);
                            hero.setY(480);
                        }
                        break;
                    // level 2
                    case 2:
                    // level 2 pass condition
                        if ((hero.getX() >= 650 && hero.getY() >= 260 && hero.getY() <= 350) && currentBackground.getEnemies().isEmpty()) {
                            currentBackground = allBackground.get(currentBackground.getCurrentScence());
                            // send the background to hero
                            hero.setBackground(currentBackground);
                            // reset hero postition
                            hero.setX(20);
                            hero.setY(480);
                        } 
                        break;
                    // level 3
                    case 3:
                        // level 3 pass condition
                        if (hero.getX() >= 680 && hero.getY() >= 445 && hero.getY() <= 565) {
                            // currentBackground = allBackground.get(currentBackground.getCurrentScence());
                            // // send the background to hero
                            // hero.setBackground(currentBackground);
                            // // reset hero postition
                            // hero.setX(20);
                            // hero.setY(495);
                            System.out.println("you win");
                            JOptionPane.showMessageDialog(this, "Congrats, you win the battle!");
                            System.exit(0);
                        }
                        break;
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

    }


}