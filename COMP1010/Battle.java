import java.util.Scanner;

public class Battle {
    Team team1;
    Team team2;
    Scanner scanner;

    public Battle(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.scanner = new Scanner(System.in);
    }

    // Recursive function to run the battle
    public void startBattle() {
        if (!team1.isAlive()) {
            System.out.println("Team 2 wins!");
            return;
        } else if (!team2.isAlive()) {
            System.out.println("Team 1 wins!");
            return;
        }

        takeTurn(team1, team2);
        if (!team2.isAlive()) {
            System.out.println("Team 1 wins!");
            return;
        }

        takeTurn(team2, team1);
        if (!team1.isAlive()) {
            System.out.println("Team 2 wins!");
            return;
        }

        // Continue the battle recursively
        startBattle();
    }

    // Handle a turn for a team
    private void takeTurn(Team attackingTeam, Team defendingTeam) {
        Character attacker = attackingTeam.getActiveMember();
        Character defender = defendingTeam.getActiveMember();

        if (attacker == null || defender == null) return;

        System.out.println("\n" + attacker.name + "'s Turn!");
        System.out.println("Choose action: 1. \n Attack 2. Defend");
        int action = scanner.nextInt();

        if (action == 1) {
            attacker.attack(defender);
        } else if (action == 2) {
            attacker.defend();
        }

        // Reset defense after the turn
        attacker.resetDefense();
    }
}
