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

    // character stand with melee weapon
    public static BufferedImage melee_weapon_left = null;
    public static BufferedImage melee_weapon_right = null;

    // character stand with ranged weapon
    public static BufferedImage ranged_weapon_left = null;
    public static BufferedImage ranged_weapon_right = null;

    // character stand with magic weapon
    public static BufferedImage magic_weapon_left = null;
    public static BufferedImage magic_weapon_right = null;

    // hero jumps to right and left
    // melee
    public static BufferedImage jump_to_right_melee = null;
    public static BufferedImage jump_to_left_melee = null;
    // ranged
    public static BufferedImage jump_to_right_ranged = null;
    public static BufferedImage jump_to_left_ranged = null;
    // magic
    public static BufferedImage jump_to_right_magic = null;
    public static BufferedImage jump_to_left_magic = null;

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

    // UI
    public static BufferedImage hero_ui = null;

    // hero death
    public static BufferedImage hero_death = null;

    // melee weapon effect
    public static List<BufferedImage> melee_effects = new ArrayList<>();

    // ranged weapon effect
    public static List<BufferedImage> ranged_effects = new ArrayList<>();

    // magic weapon effect
    public static List<BufferedImage> magic_effects = new ArrayList<>();


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

    // death
    public static BufferedImage enemy_death = null;

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
            melee_weapon_left = ImageIO.read(new File(path + "character_stand_left_melee.png"));
            melee_weapon_right = ImageIO.read(new File(path + "character_stand_right_melee.png"));
            ranged_weapon_left = ImageIO.read(new File(path + "character_stand_left_ranged.png"));
            ranged_weapon_right = ImageIO.read(new File(path + "character_stand_right_ranged.png"));
            // magic_weapon_left = ImageIO.read(new File(path + ""));
            // magic_weapon_right = ImageIO.read(new File(path + ""));

            // initialize teleporter
            teleporter = ImageIO.read(new File(path + "teleporter.png"));

            // initialize end
            end = ImageIO.read(new File(path + "end.png"));

            // initialize jump
            // melee
            jump_to_left_melee = ImageIO.read(new File(path + "jump_to_left_melee.png"));
            jump_to_right_melee = ImageIO.read(new File(path + "jump_to_right_melee.png"));
            // ranged
            jump_to_left_ranged = ImageIO.read(new File(path + "jump_to_left_ranged.png"));
            jump_to_right_ranged = ImageIO.read(new File(path + "jump_to_right_ranged.png"));
            // magic
            // jump_to_left_magic = ImageIO.read(new File(path + "jump_to_left_magic.png"));
            // jump_to_right_magic = ImageIO.read(new File(path + "jump_to_right_magic.png"));

            // initialize UI
            hero_ui = ImageIO.read(new File(path + "hero_ui.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // hero
        // initialize hero running left
        for (int i = 1; i <= 2; i++) {
            try {
                hero_run_left_melee.add(ImageIO.read(new File(path + "hero_run" + i + "_left_melee.png")));
                hero_run_left_ranged.add(ImageIO.read(new File(path + "hero_run" + i + "_left_ranged.png")));
                // hero_run_left_magic.add(ImageIO.read(new File(path + "hero_run" + i + "_left_magic.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // initialize hero running right
        for (int i = 1; i <= 2; i++) {
            try {
                hero_run_right_melee.add(ImageIO.read(new File(path + "hero_run" + i + "_right_melee.png")));
                hero_run_right_ranged.add(ImageIO.read(new File(path + "hero_run" + i + "_right_ranged.png")));
                // hero_run_right_magic.add(ImageIO.read(new File(path + "hero_run" + i + "_right_magic.png")));
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
        // hero death
        try {
            hero_death = ImageIO.read(new File(path + "hero_death.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // weapon initialization
        try {
            // melee effects
            for (int i = 1; i <= 2; i++) {
                melee_effects.add(ImageIO.read(new File(path + "melee_effects" + i + ".png")));
            }
            // ranged effects
            for (int i = 1; i <= 3; i++) {
                ranged_effects.add(ImageIO.read(new File(path + "ranged_effects" + i + ".png")));
            }
            // magic effects
            for (int i = 1; i <= 2; i++) {
                magic_effects.add(ImageIO.read(new File(path + "magic_effects" + i + ".png")));
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // initialize enemy
        // right
        for (int i = 1; i <= 2; i++) {
            try {
                enemy_run_right_melee.add(ImageIO.read(new File(path + "enemy_run" + i + "_right_melee.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // left
        for (int i = 1; i <= 2; i++) {
            try {
                enemy_run_left_melee.add(ImageIO.read(new File(path + "enemy_run" + i + "_left_melee.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // death
        try {
            enemy_death = ImageIO.read(new File(path + "enemy_death.gif"));
        } catch (IOException e) {
            e.printStackTrace();
        }




        

    }



}
