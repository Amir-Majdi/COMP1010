public class App {
    public static void main(String[] args) {
        // Create characters
        Character aang = new Character("Aang", 30, 10, 5, 8);
        Character deadpool = new Character("Deadpool", 25, 12, 4, 7);
        Character elf = new Character("Legolas", 28, 9, 6, 9);

        // Create two teams
        Team team1 = new Team();
        team1.addMember(aang);
        team1.addMember(elf);

        Team team2 = new Team();
        team2.addMember(deadpool);

        // Start battle
        Battle battle = new Battle(team1, team2);
        battle.startBattle();
    }
}

