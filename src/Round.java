import java.util.ArrayList;
import java.util.List;

public class Round {
    public List<Card> firstPlayersCards;
    public List<Card> secondPlayersCards;

    public Round() {
        this.firstPlayersCards = new ArrayList<>();
        this.secondPlayersCards = new ArrayList<>();
    }
}
