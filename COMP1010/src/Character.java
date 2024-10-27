import java.util.Random;

public class Character {
    private String name;
    private int health;
    private int strength;
    private int defense;
    private int intelligence;
    private boolean isAlive;

    public Character(String name, int health, int strength, int defense, int intelligence) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.defense = defense;
        this.intelligence = intelligence;
        this.isAlive = true;
    }

    public String getName() {
        
        return name; 
    }
    public boolean isAlive() {
        return isAlive;
    }
    public int getHealth() { 
        return health;
    }

    public void attack(Character target, BattleLog log) {
        Random random = new Random();

        // Determine miss chance: if defender's defense is higher, there’s a chance to miss
        int hitChance = this.strength - target.defense + random.nextInt(10);
        if (hitChance <= 0) {
            log.addMove(this.name + " attacks " + target.getName() + " but misses!");
            System.out.println(this.name + " attacks " + target.getName() + " but misses!");
            return;
        }

        // Calculate base damage
        int damage = Math.max(0, this.strength + random.nextInt(6) - target.defense);

        // Check for critical hit based on intelligence
        int critChance = random.nextInt(100);
        if (critChance < this.intelligence) {
            damage = damage*2;
            log.addMove(this.name + " lands a critical hit on " + target.getName() + " for " + damage + " damage!");
            System.out.println(this.name + " lands a critical hit on " + target.getName() + " for " + damage + " damage!");
        } else {
            log.addMove(this.name + " attacks " + target.getName() + " for " + damage + " damage.");
            System.out.println(this.name + " attacks " + target.getName() + " for " + damage + " damage.");
        }

        // Apply damage to the target
        target.takeDamage(damage, log);
    }

    public void takeDamage(int damage, BattleLog log) {
        health = health - damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            log.addMove(name + " has been defeated!");
            System.out.println(name + " has been defeated!");
        }
    }

    public void defend(BattleLog log) {
        log.addMove(name + " is defending and doubles their defense temporarily.");
        System.out.println(name + " is defending and doubles their defense temporarily.");
        this.defense *= 2;
    }

    public void resetDefense(int originalDefense) {
        this.defense = originalDefense;
    }

    // Function to display character's health
    public void displayHealth() {
        System.out.println(name + "'s Health: " + health + " HP");
    }
}
