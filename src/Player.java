public class Player {
    private String name;
    private int balance;
    private int bet;
    private Hand hand;
    private Hand splitHand;
    private int splitBet;

    public Player(String name) {
        this.name = name;
        this.balance = 500;
        this.bet = 0;
        this.hand = null;
        this.splitHand = null;
        this.splitBet = 0;
    }

    public void initialHand(Deck deck) {
        this.hand = new Hand(deck);
    }

    public void splitOption(Deck deck) {
        this.splitHand = new Hand(hand.removeCard(), deck.drawCard());
        this.hand = new Hand(hand.removeCard(), deck.drawCard());
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public int getBet() {
        return bet;
    }

    public Hand getHand() {
        return hand;
    }

    public Hand getSplitHand() {
        return splitHand;
    }

    public int getSplitBet() {
        return splitBet;
    }

    public void drawCard(Deck deck) {
        hand.drawCard(deck);
    }

    public boolean checkBetAmmount(int value) {
        return value <= balance && value >= 0;
    }

    public void setBet(int value) {
        if (checkBetAmmount(value)) {
            balance = balance - value;
            bet = bet + value;
        }
    }

    public void setSplitBet(int value) {
        if (checkBetAmmount(value)) {
            balance = balance - value;
            splitBet = value;
        }
    }

    public void resetBet() {
        bet = 0;
    }

    public void resetSplitBet() {
        splitBet = 0;
    }

    public void addBalance(int value) {
        balance = balance + value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int calculatePlayerHandValue() {
        return hand.handValue();
    }

    public int calculatePlayerSplitHandValue() {
        return splitHand.handValue();
    }

    public void resetHand() {
        this.hand.resetHand();
    }

    public void printHand() {
        hand.printHand();
    }

    public void printSplitHand() {
        splitHand.printHand();
    }

    public boolean canSplit() {
        return hand.canSplit();
    }

    public void drawSplitCard(Deck deck) {
        splitHand.drawCard(deck);
    }

    public void resetSplitHand() {
        this.splitHand.resetHand();
    }

    //Not mine
    public boolean hasSplit() {
        return splitHand != null && splitBet > 0;
    }
}
