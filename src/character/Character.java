/**
 * @authors Zhicun Chen, Yiqian Huang 
 */

package character;

/**
 * Character class stores character' name, hp, strenght, and defines several
 * methods.
 */
public abstract class Character {

    // character name
    private String name;
    // character total hp
    private int totalHP;
    // character strength
    private int strenght;

    /**
     * Instantiate a character object.
     * 
     * @param name     name
     * @param totalHP  total hp
     * @param strenght strength
     */
    public Character(String name, int totalHP, int strenght) {
        this.name = name;
        this.totalHP = totalHP;
        this.strenght = strenght;
    }

    /**
     * Damage to current character.
     * 
     * @param damage damage
     */
    abstract void hurted(int damage);

    /**
     * Character death.
     */
    abstract void death();

    /**
     * Character attacks.
     * 
     * @param faceRight true if the character faces to right
     */
    abstract void attack(boolean faceRight);

    /**
     * Get total hp.
     * 
     * @return total hp
     */
    public int getTotalHP() {
        return totalHP;
    }

    /**
     * Get name of the character.
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get strength of the character.
     * 
     * @return strength
     */
    public int getStrength() {
        return strenght;
    }
}
