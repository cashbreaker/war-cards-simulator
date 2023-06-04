import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        FileWriter writer;
        try {
            writer = new FileWriter("dif.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> rounds = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            List<Integer> c = resolveGame(i);
            rounds.add(c.size());
            String collect = c.stream().map(Object::toString).collect(Collectors.joining(","));
            collect += '\n';
            try {
                writer.write(collect);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            writer.close();
            writer = new FileWriter("rounds.csv");
            String collect = rounds.stream().map(Object::toString).collect(Collectors.joining(","));
            writer.write(collect);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Integer> resolveGame(int number) {
        Game game = new Game();
        game.initializeGame();
        int i = 0;
        ArrayList<Integer> player1Cards = new ArrayList<>();
        while (!game.gameEnd() && i < 50000) {
            game.resolveNewRound();
            player1Cards.add(game.p1());
            i++;
        }
        System.out.printf("Player %d won the game in %d rounds!\n",game.whoWon(),i);
        return player1Cards;
    }
}