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
    public static BufferedImage meleeWeaponLeft = null;
    public static BufferedImage meleeWeaponRight = null;

    // character stand with ranged weapon
    public static BufferedImage rangedWeaponLeft = null;
    public static BufferedImage rangedWeaponRight = null;

    // character stand with magic weapon
    public static BufferedImage magicWeaponLeft = null;
    public static BufferedImage magicWeaponRight = null;

    // hero jumps to right and left
    // melee
    public static BufferedImage jumpToRightMelee = null;
    public static BufferedImage jumpToLeftMelee = null;
    // ranged
    public static BufferedImage jumpToRightRanged = null;
    public static BufferedImage jumpToLeftRanged = null;
    // magic
    public static BufferedImage jumpToRightMagic = null;
    public static BufferedImage jumpToLeftMagic = null;

    // teleporter
    public static BufferedImage teleporter = null;

    // end
    public static BufferedImage end = null;

    // obstacles
    public static List<BufferedImage> obstacles = new ArrayList<>();

    // hero
    // character run to right with melee weapon
    public static List<BufferedImage> heroRunRightMelee = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> heroRunLeftMelee = new ArrayList<>();

    // character run to right with ranged weapon
    public static List<BufferedImage> heroRunRightRanged = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> heroRunLeftRanged = new ArrayList<>();

    // character run to right with magic weapon
    public static List<BufferedImage> heroRunRightMagic = new ArrayList<>();

    // character run to left with magic weapon
    public static List<BufferedImage> heroRunLeftMagic = new ArrayList<>();

    // UI
    public static BufferedImage heroUI = null;

    // hero death
    public static BufferedImage heroDeath = null;

    // melee weapon effect
    public static List<BufferedImage> meleeEffects = new ArrayList<>();

    // ranged weapon effect
    public static List<BufferedImage> rangedEffects = new ArrayList<>();

    // magic weapon effect
    public static List<BufferedImage> magicEffects = new ArrayList<>();


    // ememy
    // character run to right with melee weapon
    public static List<BufferedImage> enemyRunRight = new ArrayList<>();

    // character run to left with melee weapon
    public static List<BufferedImage> enemyRunLeft = new ArrayList<>();

    // bullet
    public static BufferedImage bulletLeft = null;
    public static BufferedImage bulletRight = null;

    // enemy hp bar
    public static BufferedImage enemyHP = null;

    // death
    public static List<BufferedImage> enemyDeath = new ArrayList<>();

    // enemy dialog
    public static BufferedImage enemyDialog = null;

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
            meleeWeaponLeft = ImageIO.read(new File(path + "character_stand_left_melee.png"));
            meleeWeaponRight = ImageIO.read(new File(path + "character_stand_right_melee.png"));
            rangedWeaponLeft = ImageIO.read(new File(path + "character_stand_left_ranged.png"));
            rangedWeaponRight = ImageIO.read(new File(path + "character_stand_right_ranged.png"));
            magicWeaponLeft = ImageIO.read(new File(path + "character_stand_left_magic.png"));
            magicWeaponRight = ImageIO.read(new File(path + "character_stand_right_magic.png"));

            // initialize teleporter
            teleporter = ImageIO.read(new File(path + "teleporter.png"));

            // initialize end
            end = ImageIO.read(new File(path + "end.png"));

            // initialize jump
            // melee
            jumpToLeftMelee = ImageIO.read(new File(path + "jump_to_left_melee.png"));
            jumpToRightMelee = ImageIO.read(new File(path + "jump_to_right_melee.png"));
            // ranged
            jumpToLeftRanged = ImageIO.read(new File(path + "jump_to_left_ranged.png"));
            jumpToRightRanged = ImageIO.read(new File(path + "jump_to_right_ranged.png"));
            // magic
            jumpToLeftMagic = ImageIO.read(new File(path + "jump_to_left_magic.png"));
            jumpToRightMagic = ImageIO.read(new File(path + "jump_to_right_magic.png"));

            // initialize UI
            heroUI = ImageIO.read(new File(path + "hero_ui.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // hero
        // initialize hero running left
        for (int i = 1; i <= 2; i++) {
            try {
                heroRunLeftMelee.add(ImageIO.read(new File(path + "hero_run" + i + "_left_melee.png")));
                heroRunLeftRanged.add(ImageIO.read(new File(path + "hero_run" + i + "_left_ranged.png")));
                heroRunLeftMagic.add(ImageIO.read(new File(path + "hero_run" + i + "_left_magic.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // initialize hero running right
        for (int i = 1; i <= 2; i++) {
            try {
                heroRunRightMelee.add(ImageIO.read(new File(path + "hero_run" + i + "_right_melee.png")));
                heroRunRightRanged.add(ImageIO.read(new File(path + "hero_run" + i + "_right_ranged.png")));
                heroRunRightMagic.add(ImageIO.read(new File(path + "hero_run" + i + "_right_magic.png")));
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
            heroDeath = ImageIO.read(new File(path + "hero_death.png"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // weapon initialization
        try {
            // melee effects
            for (int i = 1; i <= 2; i++) {
                meleeEffects.add(ImageIO.read(new File(path + "melee_effects" + i + ".png")));
            }
            // ranged effects
            for (int i = 1; i <= 2; i++) {
                rangedEffects.add(ImageIO.read(new File(path + "ranged_effects" + i + ".png")));
            }
            // magic effects
            for (int i = 1; i <= 2; i++) {
                magicEffects.add(ImageIO.read(new File(path + "magic_effects" + i + ".png")));
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // initialize enemy
        // walk right and left
        for (int i = 1; i <= 2; i++) {
            try {
                enemyRunRight.add(ImageIO.read(new File(path + "enemy_run" + i + "_right_melee.png")));
                enemyRunLeft.add(ImageIO.read(new File(path + "enemy_run" + i + "_left_melee.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // death, hp bar, dialog, bullet
        try {
            for (int i = 1; i <= 14; i++) {
                enemyDeath.add(ImageIO.read(new File(path + "enemy_death" + i + ".gif")));
            }
            enemyHP = ImageIO.read(new File(path + "enemy_ui.png"));
            enemyDialog = ImageIO.read(new File(path + "enemy_dialog.png"));
            bulletLeft = ImageIO.read(new File(path + "bullet_left.png"));
            bulletRight = ImageIO.read(new File(path + "bullet_right.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
