public class Dealer {
    private Card upcard;
    private Card holeCard;
    private Hand hand;

    public Dealer() {
        this.upcard = null;
        this.holeCard = null;
        this.hand = null;
    }

    public void drawUpcard(Deck deck) {
        this.upcard = deck.drawCard();
    }

    public void drawHoleCard(Deck deck) {
        this.holeCard = deck.drawCard();
        this.hand = new Hand(upcard, holeCard);
    }

    public void drawCard(Deck deck) {
        this.hand.drawCard(deck);
    }

    public Card getUpcard() {
        return upcard;
    }

    public void printUpcard() {
        upcard.printCard();
    }

    public void printHoleCard() {
        holeCard.printCard();
    }

    public Card getHoleCard() {
        return holeCard;
    }

    public Hand getHand() {
        return hand;
    }

    public int calculateDealerHandValue() {
        return hand.handValue();
    }

    public void resetHand() {
        this.upcard = null;
        this.holeCard = null;
        this.hand.resetHand();
    }

    public void printHand() {
        hand.printHand();
    }

    public boolean softHand() {
        return hand.softHand();
    }
}
