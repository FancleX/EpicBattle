package Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.Graphics;

import javax.swing.JFrame;

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
    long firstPress = 0;
    long secondPress = 1500;

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
        hero = new Hero("Jack", 100, 100, 100);
        hero.setX(20);
        hero.setY(490);
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
            if (secondPress - firstPress >= 1500) {
                firstPress = System.currentTimeMillis();
                hero.jump();
            }
        }
        secondPress = System.currentTimeMillis();

        // //if press "↓", then fall
        // if (e.getKeyCode() == 36) {
        //     hero.fall();
        // }
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
        
    }


    @Override
    public void run() {
        while (true) {
            // refresh the window
            repaint();
            try {
                Thread.sleep(50);
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
                            hero.setY(490);
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
                            hero.setY(490);
                        } 
                        break;
                    // level 3
                    case 3:
                        if (hero.getX() >= 610 && hero.getY() >= 445 && hero.getY() <= 565) {
                            // currentBackground = allBackground.get(currentBackground.getCurrentScence());
                            // // send the background to hero
                            // hero.setBackground(currentBackground);
                            // // reset hero postition
                            // hero.setX(20);
                            // hero.setY(495);
                            System.out.println("you win");
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