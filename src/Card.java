public class Card {
    private String suit;
    private String rank;
    private int value;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        switch (rank) {
            case "A":
                this.value = 11;
                break;
            case "K":
            case "Q":
            case "J":
                this.value = 10;
                break;
            default:
                this.value = Integer.parseInt(rank);
                break;
        }
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public void printCard() {
        System.out.println("Suit : " + this.getSuit());
        System.out.println("Rank : " + this.getRank());
        System.out.println("Vaule: " + this.getValue());
    }
}
