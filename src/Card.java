public class Card implements Comparable<Card> {
    private Value value;
    private Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public String toString() {
        return String.format("%-2s", value.symbol) + " " + String.format("%-8s", suit);
    }
}
