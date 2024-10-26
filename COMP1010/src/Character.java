import java.util.Random;

public class Character {
    String name;
    int health;
    int strength;
    int defense;
    int intelligence;
    boolean isAlive;

    public Character(String name, int health, int strength, int defense, int intelligence) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.intelligence = intelligence;
        this.isAlive = true;
    }

    // Attack another character
    public void attack(Character target) {
        Random random = new Random();
        int damage = (this.strength + random.nextInt(6)) - target.defense;
        if (damage < 0) damage = 0;
        System.out.println(this.name + " attacks " + target.name + " for " + damage + " damage!");
        target.takeDamage(damage);
    }

    // Take damage and reduce health
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.isAlive = false;
            System.out.println(this.name + " has been defeated!");
        } else {
            System.out.println(this.name + " has " + this.health + " HP remaining.");
        }
    }

    // Defend action (increase defense temporarily)
    public void defend() {
        System.out.println(this.name + " is defending, doubling defense!");
        this.defense *= 2;
    }

    // Reset defense after each turn
    public void resetDefense() {
        this.defense /= 2;
    }
}
