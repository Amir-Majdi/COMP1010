import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Character> members;

    public Team(String name) {
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addMember(Character character) {
        members.add(character);
    }

    // Recursive method to check if the team has any alive members
    public boolean hasAliveMembers() {
        return hasAliveMembers(0);
    }

    private boolean hasAliveMembers(int index) {
        if (index >= members.size()) return false;
        if (members.get(index).isAlive()) return true;
        return hasAliveMembers(index + 1);
    }

    public Character getFirstAliveMember() {
        return getFirstAliveMember(0);
    }

    private Character getFirstAliveMember(int index) {
        if (index >= members.size()) return null;
        Character character = members.get(index);
        if (character.isAlive()) return character;
        return getFirstAliveMember(index + 1);
    }
}
