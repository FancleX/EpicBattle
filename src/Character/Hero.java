package Character;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

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
    /* status can be 
    "melee_weapon_right": hero holds melee weapon and towards right
    "melee_weapon_left": hero holds melee weapon and towards left
    "ranged_weapon_right": hero holds ranged weapon and towards right
    "ranged_weapon_left": hero holds ranged weapon and towards left
    "magic_weapon_right": hero holds magic weapon and towards right
    "magic_weapon_left": hero holds magic weapon and towards left
    "jump" : is jumping right now
    "jump_left": jump to left
    "jump_right": jump to right
    "move_left": move to left
    "move_right": move to right
    "stop_left": stop and towards left
    "stop_right": stop and towards right
    */
    private String status;
    // the picture corresponding to the current status
    private BufferedImage image = StaticValue.melee_weapon_left;
    // get obstacle information
    private Background background = new Background();
    // hero UI
    private BufferedImage ui = null;
    // create thread to drive hero
    private Thread thread = null;
    // moving speed on x-axis
    private int xSpeed;
    // jumping speed on y-axis
    private int ySpeed;
    // define an index to get image of moving images
    private int index;
    // jumping time
    private int jumpingTime;
    // height
    private int height = 60;
    // width
    private int width = 40;
    // current hp
    private int currentHp;
    // current mana
    private int currentMana;
    // hero is alive
    private boolean isAlive = true;

    public Hero(String name, int strenght, int hp, int mana) {
        super.strenght = strenght;
        super.hp = hp;
        super.mana = mana;
        this.name = name;
        this.strenght = strenght;
        this.hp = hp;
        this.mana = mana;
        this.currentHp = hp;
        this.currentMana = mana;

        image = StaticValue.melee_weapon_right;
        this.status = "melee_weapon_right";
        ui = StaticValue.hero_ui;
        thread = new Thread(this);
        thread.start();
    }

    // hero moves to left
    public void moveLeft() {
        // change speed to nagative 
        xSpeed = -10;
        // if the hero is jumping
        if (status != "jump") {
            status = "move_left";
        } else {
            status = "jump_left";
        }
    }

    // hero moves to right
    public void moveRight() {
        xSpeed = 10;
        if (status != "jump") {
            status = "move_right";
        } else {
            status = "jump_right";
        }
    }

    // hero stops at left
    public void stopLeft() {
        // change xSpeed to zero
        xSpeed = 0;
        // if is jumping
        if (status != "jump") {
            status = "stop_left";
        } else {
            status = "jump_left";
        }
    }

    // hero stops at right
    public void stopRight() {
        xSpeed = 0;
        if (status != "jump") {
            status = "stop_right";
        } else {
            status = "jump_right";
        }
    }

    // hero jumps up
    public void jump() {
        // if is jumping
        if (status != "jump") {
            // if jump to left
            if (status.contains("left")) {
                status = "jump_left";
            } else {
                status = "jump_right";
            }
            ySpeed = -12;
            jumpingTime = 10;
            int height = ySpeed * jumpingTime;
            y += height;
        }  
    }

    // hero falls down
    public void fall() {
        // detect if is on ground and stop falling on the ground
        if (status.contains("left")) {
            status = "jump_left";
        } else {
            status = "jump_right";
        }
        ySpeed = 7;
        // change position when moving on jumping
        y += ySpeed;
    }

    // death
    public void death() {
        image = StaticValue.hero_death;
        isAlive = false;
    }

    // hero orientation
    public boolean faceRight() {
        if (status.contains("right")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        while (true) {
            // determine hero's action 
            boolean isOnObstacle = false;
            boolean passLeft = true;
            boolean passRight = true;
            // iterate obstacles 
            for (int i = 0; i < background.getObstacleList().size(); i++) {
                Obstacle obstacle = background.getObstacleList().get(i);
                // determine if hero is on obstacles
                // if (obstacle.toRectangle().intersects(toRectangle())) {
                //     isOnObstacle = true;
                // }
                // System.err.println("obstacleY: " + obstacle.getY() + " obstacleType: " + obstacle.getType());
                // System.err.println("heroY: " + this.y);
                // obstacle.getY() > this.y + 45 && obstacle.getY() < this.y + 55
                if ((obstacle.toRectangle().intersects(toRectangle())) && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY() + obstacle.getWidth() + 15)) {
                    isOnObstacle = true;
                }
                // determine if hero can walk right head: 494  ground: 550 hero width: 70
                // System.err.println("obstacleY: " + obstacle.getY() + " type: " + obstacle.getType());
                // System.err.println("heroY: " + y);
                // obstacle.getX() == this.x + 70
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passRight = false;
                }
                // determine if hero can walk left
                // System.err.println("obstacleX: " + obstacle.getX());
                // System.err.println("heroX: " + this.x);
                // System.err.println("obstacleWidth: " + obstacle.getWidth() + " obstacleType: " + obstacle.getType());
                // obstacle.getX() == this.x - obstacle.getWidth() - 30
                // (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)
                if (obstacle.toRectangle().intersects(toRectangle()) && (obstacle.getY() > this.y - 5 && obstacle.getY() < this.y + 55)) {
                    passLeft = false;
                }
                // System.err.println(passRight);
                // determine if hero is on the bottom of the obstacle
                // if ((obstacle.getY() <= this.y - obstacle.getHeight() - 5 && obstacle.getY() >= this.y - 5 ) && (this.x > obstacle.getX() - 15 && this.x < obstacle.getY() + obstacle.getWidth() + 15)) {
                //     jumpingTime = 0;
                // }
            }

            // // attack and injured from enemies
            // for (int i = 0; i < background.getEnemies().size(); i++) {
            //     Enemy enemy = background.getEnemies().get(i);
            //     // if attack a enemy 
            //     if ()
            // }



            // display the action of jumping
            // stand on the obstacle
            if (isOnObstacle && jumpingTime == 0) {
                if (status.contains("left")) {
                    // move to left
                    if (xSpeed != 0) {
                        status = "move_left";
                    } else {
                        // stop at left
                        status = "stop_left";
                    }
                } else {
                    // move to right
                    if (xSpeed != 0) {
                        status = "move_right";
                    } else {
                        // stop at right
                        status = "stop_right";
                    }
                }
            } else {
                // hero is jumping and need to fall down
                if (jumpingTime != 0) {
                    jumpingTime--;
                } else {
                    // hero jumps to the most top
                    fall();
                } 
            }

            // if hero is moving
            if ((xSpeed < 0 && passLeft) || (xSpeed > 0 && passRight)) {
                x += xSpeed;
                // boundary detection, if the hero runs to the left and right of the window
                // left boundary
                if (x < 0) {
                    x = 0;
                }
                // right boundary
                else if (x > 750) {
                    x = 750;
                }
            }

            // connect images with moving status
            // rotate the index from 0 to 1 to repeat displaying our moving images
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }
            // detect status and assign the images of the status
            switch (status) {
                // move to left
                case "move_left":
                    image = StaticValue.hero_run_left_melee.get(index);
                    break;
                // move to right
                case "move_right":
                    image = StaticValue.hero_run_right_melee.get(index);
                    break;
                // stop and towards left
                case "stop_left":
                    image = StaticValue.melee_weapon_left;
                    break;
                // stop and towards right
                case "stop_right":
                    image = StaticValue.melee_weapon_right;
                    break;
                // jump to left
                case "jump_left":
                    image = StaticValue.jump_to_left;
                    break;
                // jump to right
                case "jump_right":
                    image = StaticValue.jump_to_right;
                    break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        
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

    public Background getBackground() {
        return background;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public Rectangle toRectangle() {
        return new Rectangle(x, y, width, height);
    }

    public BufferedImage getUI() {
        return ui;
    }

    public String getName() {
        return name;
    }

    public int getCurrentlHp() {
        return currentHp;
    }

    public void setCurrentHp(int hp) {
        this.currentHp = hp;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int mana) {
        this.currentMana = mana;
    }

    public boolean isAlive() {
        return isAlive;
    }

}
