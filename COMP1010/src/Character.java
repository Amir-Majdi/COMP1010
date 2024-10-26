import java.util.Random;

public class Character {
    String name;
    int health;
    int strength;
    int defense;
    int intelligence;  // Intelligence now plays a role
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
        
        // Use intelligence to add chance of critical hit
        boolean criticalHit = random.nextInt(100) < this.intelligence * 2;  // Intelligence increases critical chance

        int baseDamage = (this.strength + random.nextInt(6)) - target.defense;
        if (baseDamage < 0) baseDamage = 0;

        if (criticalHit) {
            baseDamage *= 2;  // Double the damage on a critical hit
            System.out.println(this.name + " landed a CRITICAL HIT on " + target.name + " for " + baseDamage + " damage!");
        } else {
            System.out.println(this.name + " attacks " + target.name + " for " + baseDamage + " damage!");
        }

        target.takeDamage(baseDamage);
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

