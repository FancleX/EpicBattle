package Frame;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import Character.Enemy;

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
    
    public Background() {}

    public Background(int currentScence, boolean isFinal) {
        this.currentScence = currentScence;
        this.isFinal = isFinal;
        
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
        switch (currentScence){
             /* draw ground and obstacles of the first scence 
                ground: type 0
                obstacle1: type 1
                obstacle2: type 2
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
                enemies.add(new Enemy(650, 480, false, 0, this));
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
                break;

            case 3:
                // ground
                obstacleList.add(new Obstacle(0, 550, 800, 50, 0, this));
                // end teleporter
                end = StaticValue.end;
                break;
        }
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public int getCurrentScence() {
        return currentScence;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public BufferedImage getTeleporter() {
        return teleporter;
    }

    public int getTeleporterX() {
        return teleporterX;
    }

    public int getTeleporterY() {
        return teleporterY;
    }

    public BufferedImage getEnd() {
        return end;
    }

    public boolean getIsFinal() {
        return isFinal;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

}
