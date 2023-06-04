import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int i;
    public static void main(String[] args) {
        FileWriter writer;
        try {
            writer = new FileWriter("dif.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> rounds = new ArrayList<>();
        int numberOfGames;
        int deckSize = 1;
        if (args.length != 0) {
            numberOfGames = Integer.parseInt(args[0]);
            if (args.length != 1) {
                deckSize = Integer.parseInt(args[1]);
            }
        } else {
            numberOfGames = 10000;
        }
        i = 0;
        new Thread(() -> {
            while (i < numberOfGames) {
                printProgress(i,numberOfGames);
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        for (i = 0; i < numberOfGames; i++) {
            List<Integer> c = resolveGame(i,deckSize);
            rounds.add(c.size());
            //String collect = c.stream().map(Object::toString).collect(Collectors.joining(","));
            //collect += '\n';
            //try {
                //writer.write(collect);
            //} catch (IOException e) {
               // throw new RuntimeException(e);
            //}
        }
        try {
            //writer.close();
            writer = new FileWriter("rounds.csv");
            String collect = rounds.stream().map(Object::toString).collect(Collectors.joining(","));
            writer.write(collect);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printProgress(int current, int max) {
        int i = 100*current/max;
        System.out.print("[");
        int j=0;
        while(j++<i){
            System.out.print("#");
        }
        while(j++<100){
            System.out.print(" ");
        }
        System.out.printf("] : %d/%d %d%%",current,max,i);
        System.out.print("\r");
    }

    public static ArrayList<Integer> resolveGame(int number, int deckSize) {
        Game game = new Game(deckSize);
        game.initializeGame();
        int i = 0;
        ArrayList<Integer> player1Cards = new ArrayList<>();
        while (!game.gameEnd() && i < 10000*Math.pow(2,deckSize)) {
            game.resolveNewRound();
            player1Cards.add(game.p1());
            i++;
        }
        //System.out.printf("Player %d won the game in %d rounds!\n",game.whoWon(),i);
        return player1Cards;
    }
}