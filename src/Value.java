public enum Value implements Comparable<Value> {
    Two(2,"2"),
    Three(3,"3"),
    Four(4,"4"),
    Five(5,"5"),
    Six(6,"6"),
    Seven(7,"7"),
    Eight(8,"8"),
    Nine(9,"9"),
    Ten(10,"10"),
    Jack(11,"J"),
    Queen(12,"Q"),
    King(13,"K"),
    Ace(14,"A")
    ;
    public final int integerValue;
    public final String symbol;

    Value(int integerValue, String symbol) {
        this.integerValue = integerValue;
        this.symbol = symbol;
    }
}
