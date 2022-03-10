package Frame;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class StaticValue {
    // set background
    public static BufferedImage background1 = null;
    public static BufferedImage background2 = null;
    public static BufferedImage background3 = null;

    // character with melee weapon
    public static BufferedImage melee_weapon_left = null;
    public static BufferedImage melee_weapon_right = null;

    // character with ranged weapon
    public static BufferedImage ranged_weapon_left = null;
    public static BufferedImage ranged_weapon_right = null;

    // character with magic weapon
    public static BufferedImage magic_weapon_left = null;
    public static BufferedImage magic_weapon_right = null;

    // hero jumps to right
    public static BufferedImage jump_to_right = null;

    // hero jumps to left
    public static BufferedImage jump_to_left = null;

    // teleporter
    public static BufferedImage teleporter = null;

    // end
    public static BufferedImage end = null;

    // obstacles
    public static List<BufferedImage> obstacles = new ArrayList<>();

    // hero
    // character run to right with melee weapon
    public static List<BufferedImage> hero_run_right_melee = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> hero_run_left_melee = new ArrayList<>();

    // character run to right with ranged weapon
    public static List<BufferedImage> hero_run_right_ranged = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> hero_run_left_ranged = new ArrayList<>();

    // character run to right with magic weapon
    public static List<BufferedImage> hero_run_right_magic = new ArrayList<>();

    // character run to left with magic weapon
    public static List<BufferedImage> hero_run_left_magic = new ArrayList<>();


    // ememy
    // character run to right with melee weapon
    public static List<BufferedImage> enemy_run_right_melee = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> enemy_run_left_melee = new ArrayList<>();

    // character run to right with ranged weapon
    public static List<BufferedImage> enemy_run_right_ranged = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> enemy_run_left_ranged = new ArrayList<>();

    // character run to right with magic weapon
    public static List<BufferedImage> enemy_run_right_magic = new ArrayList<>();

    // character run to left with magic weapon
    public static List<BufferedImage> enemy_run_left_magic = new ArrayList<>();

    // path define
    public static String path = System.getProperty("user.dir") + "/src/images/";


    // initialize
    public static void initializer() {
        try {
            // initialize background images
            background1 = ImageIO.read(new File(path + "background1.jpg"));
            background2 = ImageIO.read(new File(path + "background2.jpg"));
            background3 = ImageIO.read(new File(path + "background3.jpg"));

            // initialize hero stands up with different weapon images
            melee_weapon_left = ImageIO.read(new File(path + "character_left.png"));
            melee_weapon_right = ImageIO.read(new File(path + "character_right.png"));
            // ranged_weapon_left = ImageIO.read(new File(path + ""));
            // ranged_weapon_right = ImageIO.read(new File(path + ""));
            // magic_weapon_left = ImageIO.read(new File(path + ""));
            // magic_weapon_right = ImageIO.read(new File(path + ""));

            // initialize teleporter
            teleporter = ImageIO.read(new File(path + "teleporter.png"));

            // initialize end
            end = ImageIO.read(new File(path + "end.png"));

            // initialize jump
            jump_to_left = ImageIO.read(new File(path + "jump_to_left.png"));
            jump_to_right = ImageIO.read(new File(path + "jump_to_right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // initialize hero running left
        for (int i = 1; i <= 2; i++) {
            try {
                hero_run_left_melee.add(ImageIO.read(new File(path + "hero_run" + i + "_left.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // initialize hero running right
        for (int i = 1; i <= 2; i++) {
            try {
                hero_run_right_melee.add(ImageIO.read(new File(path + "hero_run" + i + "_right.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // initialize obstacles and ground
        try {
            obstacles.add(ImageIO.read(new File(path + "ground.jpg")));
            for (int i = 1; i <= 2; i++) {
                obstacles.add(ImageIO.read(new File(path + "obstacle" + i + ".png")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        // initialize enemy



        

    }



}
