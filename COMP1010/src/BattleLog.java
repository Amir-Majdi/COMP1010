import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
// Creates the battlelog
public class BattleLog {
    private ArrayList<String> moves;

    public BattleLog() {
        this.moves = new ArrayList<>();
    }

    public void addMove(String move) {
        moves.add(move);
    }
// Saves battle log to "moves.txt"
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String move : moves) {
                writer.write(move + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the battle log.");
        }
    }
}
