/**
 * @codeImplementation Zhicun Chen
 * @characterActionDesign Yiqian Huang
 */

package frame;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import character.Enemy;

/**
 * Background takes care of game background and shares background's info with
 * characters and mian frame.
 * Background has ground and obstacles settings for each level, and stores
 * enemies' info.
 */
public class Background {

    // current background
    private BufferedImage backgroundImage = null;
    // current scence
    private int currentScence;
    // if is the final scence
    private boolean isFinal;
    // store all obstacles
    private List<Obstacle> obstacleList = new ArrayList<>();
    // teleporter
    private BufferedImage teleporter = null;
    // teleporter location
    private int teleporterX;
    private int teleporterY;
    // end
    private BufferedImage end = null;
    // enemies
    private List<Enemy> enemies = new ArrayList<>();
    // random
    private Random rd = new Random();

    /**
     * Empty constructor for instantiating an object without specific info.
     */
    public Background() {
    }

    /**
     * Instantiate a background that has current level, and if is the final level.
     * Also complete level scene and enemy settings.
     * 
     * @param currentScence current level
     * @param isFinal       last level
     */
    public Background(int currentScence, boolean isFinal) {
        this.currentScence = currentScence;
        this.isFinal = isFinal;

        // give different background pictures to different level
        if (isFinal) {
            backgroundImage = StaticValue.background3;
        } else {
            if (currentScence == 1) {
                backgroundImage = StaticValue.background1;
            } else {
                backgroundImage = StaticValue.background2;
            }
        }

        // level settings
        switch (currentScence) {
            /*
             * draw ground and obstacles of the first scence
             * ground: type 0
             * obstacle1: type 1
             * obstacle2: type 2
             */
            case 1:
                // ground
                obstacleList.add(new Obstacle(0, 550, 800, 50, 0, this));
                // add obstacle 1
                for (int i = 170; i <= 600; i += 300) {
                    obstacleList.add(new Obstacle(i, 450, 154, 28, 2, this));
                }
                // add teleporter at (750, 450)
                teleporter = StaticValue.teleporter;
                teleporterX = 750;
                teleporterY = 450;
                // add enemies
                enemies.add(new Enemy(500, 492, false, this, rd.nextInt(3)));
                enemies.add(new Enemy(600, 492, false, this, rd.nextInt(3)));
                enemies.add(new Enemy(700, 492, false, this, rd.nextInt(3)));
                break;

            case 2:
                // ground
                obstacleList.add(new Obstacle(0, 550, 800, 50, 0, this));
                // add obstacle 1
                int j = 500;
                for (int i = 250; i <= 600; i += 100) {
                    obstacleList.add(new Obstacle(i, j, 154, 44, 1, this));
                    j -= 50;
                }
                // add teleporter at (660, 250)
                teleporter = StaticValue.teleporter;
                teleporterX = 660;
                teleporterY = 250;
                // add enemies
                enemies.add(new Enemy(650, 492, false, this, rd.nextInt(3)));
                break;

            case 3:
                // ground
                obstacleList.add(new Obstacle(0, 550, 800, 50, 0, this));
                // end teleporter
                end = StaticValue.end;
                // add enemies
                enemies.add(new Enemy(650, 492, false, this, rd.nextInt(3)));
                enemies.add(new Enemy(600, 492, false, this, rd.nextInt(3)));
                break;
        }
    }

    /**
     * Get the image of the background.
     * 
     * @return the image of the background
     */
    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Get current scence.
     * 
     * @return current scence
     */
    public int getCurrentScence() {
        return currentScence;
    }

    /**
     * Get if is the final scence.
     * 
     * @return true if is the final scence
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Get obstacles in the background.
     * 
     * @return a list of obstacles
     */
    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    /**
     * Get the image of the teleporter.
     * 
     * @return image of the teleporter
     */
    public BufferedImage getTeleporter() {
        return teleporter;
    }

    /**
     * Get x-axis coordinate of the teleporter.
     * 
     * @return x-axis coordinate of the teleporter
     */
    public int getTeleporterX() {
        return teleporterX;
    }

    /**
     * Get y-axis coordinate of the teleporter.
     * 
     * @return y-axis coordinate of the teleporter
     */
    public int getTeleporterY() {
        return teleporterY;
    }

    /**
     * Get the image of the teleporter in the last scence.
     * 
     * @return image of the telepoerter in the last scence
     */
    public BufferedImage getEnd() {
        return end;
    }

    /**
     * Get enemies in the background.
     * 
     * @return a list of enemies
     */
    public List<Enemy> getEnemies() {
        return enemies;
    }
}
