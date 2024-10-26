import java.util.ArrayList;

public class Team {
    ArrayList<Character> members;

    public Team() {
        members = new ArrayList<>();
    }

    // Add character to the team
    public void addMember(Character character) {
        members.add(character);
    }

    // Check if the team is still alive
    public boolean isAlive() {
        for (Character member : members) {
            if (member.isAlive) {
                return true;
            }
        }
        return false;
    }

    // Get an alive character to take a turn
    public Character getActiveMember() {
        for (Character member : members) {
            if (member.isAlive) {
                return member;
            }
        }
        return null;  // All members are defeated
    }
}
