import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> hand = new ArrayList<>();

    public Hand(Deck deck) {
        hand.add(deck.drawCard());
//        hand.add(Deck.drawCard());
    }

    public Hand(Card upcard, Card holeCard) {
        hand.add(upcard);
        hand.add(holeCard);
    }

    public Card removeCard() {
        Card card = hand.getLast();
        hand.removeLast();

        return card;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Deck deck) {
        hand.add(deck.drawCard());
    }

    public int handValue() {
        int handValue = 0;
        int aceCounter = 0;

        for (Card card : hand) {
            handValue = handValue + card.getValue();
            if (card.getRank().equals("A")) {
                aceCounter++;
            }
        }

        for (int i = 0; i < aceCounter; i++) {
            if (handValue + 10 <= 21) {
                handValue = handValue + 10;
            }
        }

        return handValue;
    }

    public void resetHand() {
        hand.clear();
    }

    public void printHand() {
        for (Card card: hand) {
            card.printCard();
        }
    }

    public int handCount() {
        return hand.size();
    }

    public boolean canSplit() {
        return hand.size() == 2 && hand.get(0).getValue() == hand.get(1).getValue();
    }

    public boolean softHand() {
        int handValue = 0;
        int aceCounter = 0;
        boolean checkSoft = false;

        for (Card card : hand) {
            handValue = handValue + card.getValue();
            if (card.getRank().equals("A")) {
                aceCounter++;
            }
        }

        if (aceCounter > 0) {
            for (int i = 0; i < aceCounter; i++) {
                if (handValue + 10 <= 21) {
                    handValue = handValue + 10;
                    if (handValue == 17) {
                        checkSoft = true;
                    } else {
                        checkSoft = false;
                    }
                }
            }
            return checkSoft;
        } else return false;
    }
}
