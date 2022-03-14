package Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Character.Enemy;
import Character.Hero;

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
    long firstJump = 0;
    long secondJump = 1500;
    // limit attack gap
    long firstAttack = 0;
    long secondAttack = 200;

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
        // start thread
        thread.start();
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
        graphics.fillRect(110, 75, (int) ((float) hero.getCurrentlHp() / hero.getHp() * 165), 5);
        // draw mana
        graphics.setColor(Color.BLUE);
        graphics.fillRect(110, 87, (int) ((float) hero.getCurrentMana() / hero.getMana() * 165), 5);

        // draw enemies
        for (Enemy enemy : currentBackground.getEnemies()) {
            graphics.drawImage(enemy.getCurrentImage(), enemy.getX(), enemy.getY(), this);
        }

        // draw attack effects
        if (hero.isAttacking()) {
            if (hero.faceRight()) {
                graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() + 35, hero.getY() - 10, this);
            } else {
                graphics.drawImage(hero.getCurrentWeaponEffects(), hero.getX() - 30, hero.getY() - 10, this);
            }
        } 
        
        // connect off screen image to window
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
                        if (hero.getX() >= 740 && hero.getY() >= 460 && hero.getY() <= 550) {
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
                        if (hero.getX() >= 650 && hero.getY() >= 260 && hero.getY() <= 350) {
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