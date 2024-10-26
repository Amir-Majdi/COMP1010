import java.util.Scanner;

public class App {   
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        BattleLog log = new BattleLog();

        // Character creation menu
        System.out.println("Choose character creation option:");
        System.out.println("1. Use predefined characters");
        System.out.println("2. Create custom characters");
        int choice = 0;

        while (choice != 1 && choice != 2) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
            } else {
                scanner.next();  // Clear the invalid input
            }
            if (choice != 1 && choice != 2) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }
        

        Character aang, deadpool;

        if (choice == 1) {
            // Use predefined characters
            aang = new Character("Aang", 30, 10, 5, 8);
            deadpool = new Character("Deadpool", 25, 12, 4, 7);
            System.out.println("Using predefined characters.");
        } else {
            // Custom character creation with points to allocate
            System.out.println("Enter stats for the Hero character:");
            aang = createCustomCharacter(scanner, "Hero");

            System.out.println("Enter stats for the Villain character:");
            deadpool = createCustomCharacter(scanner, "Villain");
        }

        // Initialize teams
        Team heroTeam = new Team("Heroes");
        Team villainTeam = new Team("Villains");
        heroTeam.addMember(aang);
        villainTeam.addMember(deadpool);

        // Game loop
        while (heroTeam.hasAliveMembers() && villainTeam.hasAliveMembers()) {
            Character hero = heroTeam.getFirstAliveMember();
            Character villain = villainTeam.getFirstAliveMember();

            // Hero's turn
            System.out.println("\n--- Hero's Turn ---");
            int action = 0;
            while (action != 1 && action != 2) {
                System.out.println("Choose action: 1. Attack 2. Defend");
                if (scanner.hasNextInt()) {
                    action = scanner.nextInt();
                    if (action != 1 && action != 2) {
                        System.out.println("Invalid choice. Please enter 1 to Attack or 2 to Defend.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number (1 or 2).");
                    scanner.next();
                }
            }

            if (action == 1) {
                hero.attack(villain, log);
            } else if (action == 2) {
                hero.defend(log);
            }

            // Display health after hero's turn
            System.out.println("\n[Health Status After Hero's Turn]");
            hero.displayHealth();
            villain.displayHealth();

            if (!villain.isAlive()) continue;

            // Villain's turn
            System.out.println("\n--- Villain's Turn ---");
            villain.attack(hero, log);

            // Display health after villain's turn
            System.out.println("\n[Health Status After Villain's Turn]");
            hero.displayHealth();
            villain.displayHealth();

            // Reset defense after each turn
            hero.resetDefense(5);  // Reset to initial defense
        }

        // Check game outcome
        if (heroTeam.hasAliveMembers()) {
            log.addMove("Victory! The hero team wins!");
            System.out.println("Victory! The hero team wins!");
        } else {
            log.addMove("Defeat! The villain team wins!");
            System.out.println("Defeat! The villain team wins!");
        }

        // Save battle log
        log.saveToFile("moves.txt");
        scanner.close();
    }

    // Method to create a custom character with points to allocate
    public static Character createCustomCharacter(Scanner scanner, String characterRole) {
        System.out.println("Enter name for " + characterRole + ":");
        String name = scanner.next();

        int points = 100;
        int health = 0, strength = 0, defense = 0, intelligence = 0;

        System.out.println("You have 100 points to allocate between health, strength, defense, and intelligence.");
        
        health = allocateStat(scanner, "health", points);
        points -= health;
        System.out.println("Points remaining after health allocation: " + points);

        if (points > 0) {
            strength = allocateStat(scanner, "strength", points);
            points -= strength;
            System.out.println("Points remaining after strength allocation: " + points);
        }

        if (points > 0) {
            defense = allocateStat(scanner, "defense", points);
            points -= defense;
            System.out.println("Points remaining after defense allocation: " + points);
        }

        if (points > 0) {
            intelligence = allocateStat(scanner, "intelligence", points);
            points -= intelligence;
            System.out.println("Points remaining after intelligence allocation: " + points);
        }

        System.out.println(name + " has been created with the following stats:");
        System.out.println("Health: " + health + ", Strength: " + strength + ", Defense: " + defense + ", Intelligence: " + intelligence);

        return new Character(name, health, strength, defense, intelligence);
    }

    // Helper method to allocate points for a specific stat
    private static int allocateStat(Scanner scanner, String statName, int remainingPoints) {
        int statValue = -1;

        while (statValue < 0 || statValue > remainingPoints) {
            System.out.println("Enter points for " + statName + " (remaining points: " + remainingPoints + "):");
            if (scanner.hasNextInt()) {
                statValue = scanner.nextInt();
                if (statValue < 0 || statValue > remainingPoints) {
                    System.out.println("Invalid input. You must allocate between 0 and " + remainingPoints + " points.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }

        return statValue;
    }
}