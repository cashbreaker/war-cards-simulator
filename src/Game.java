import java.util.*;

public class Game {
    private Queue<Card> firstPlayer;
    private Queue<Card> secondPlayer;
    private List<Round> roundList;

    public Game() {
        this.firstPlayer = new LinkedList<>();
        this.secondPlayer = new LinkedList<>();
        this.roundList = new ArrayList<>();
    }

    public void initializeGame() {
        List<Card> deck = new ArrayList<>();
        for (Suit suit:Suit.values()) {
            for(Value value:Value.values()) {
                deck.add(new Card(value,suit));
            }
        }
        Collections.shuffle(deck);

        for (int i = 0; i < deck.size(); i++) {
            if (i%2==0) {
                firstPlayer.add(deck.get(i));
            } else {
                secondPlayer.add(deck.get(i));
            }
        }
    }

    public void resolveNewRound() {
        Round round = new Round();
        roundList.add(round);
        Card f = firstPlayer.remove();
        Card s = secondPlayer.remove();
        round.firstPlayersCards.add(f);
        round.secondPlayersCards.add(s);
//        System.out.printf("Round %d\n",roundList.size());
//        System.out.printf("%s vs %s\n",f,s);
        while (!determineWinner(round)) {
            f = firstPlayer.remove();
            s = secondPlayer.remove();
            round.firstPlayersCards.add(f);
            round.secondPlayersCards.add(s);
//            System.out.printf("%s || %s\n",f,s);
            f = firstPlayer.remove();
            s = secondPlayer.remove();
            round.firstPlayersCards.add(f);
            round.secondPlayersCards.add(s);
//            System.out.printf("%s vs %s\n",f,s);
        }
//        System.out.printf("%d to %d\n",firstPlayer.size(),secondPlayer.size());
    }

    public boolean gameEnd() {
        return firstPlayer.size()==0 || secondPlayer.size() == 0;
    }

    public int whoWon() {
        if (firstPlayer.size()==0) {
            return 2;
        }
        if (secondPlayer.size()==0) {
            return 1;
        }
        return -1;
    }

    private boolean determineWinner(Round round) {
        int result = round.firstPlayersCards.get(round.firstPlayersCards.size()-1)
                .compareTo(round.secondPlayersCards.get(round.secondPlayersCards.size()-1));
        if (result == 0) {
            if (firstPlayer.size()>1&&secondPlayer.size()>1) {
                return false;
            }
            firstPlayer.addAll(round.firstPlayersCards);
            secondPlayer.addAll(round.secondPlayersCards);
            return true;
        }
        if (result > 0) {
            List<Card> c = new ArrayList<>();
            c.addAll(round.firstPlayersCards);
            c.addAll(round.secondPlayersCards);
            Collections.shuffle(c);
            firstPlayer.addAll(c);
            //System.out.print("Player 1 wins!!!\n");
        } else {
            List<Card> c = new ArrayList<>();
            c.addAll(round.firstPlayersCards);
            c.addAll(round.secondPlayersCards);
            Collections.shuffle(c);
            secondPlayer.addAll(c);
//            System.out.print("Player 2 wins!!!\n");
        }
        return true;
    }

    public int p1() {
        return firstPlayer.size();
    }

//    private void printCards(Card one, Card two) {
//        System.out.printf("%s vs %s\n",f,s);
//
//    }
}
