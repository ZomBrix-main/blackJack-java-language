import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Card> deck = Deck.getDeck();
        System.out.println("Talia niepotasowana:");
        System.out.println();

        for (Card card : deck) {
            card.printCard();
        }
        System.out.println("Talia potasowana:");
        deck = Deck.shuffle();

        for (Card card : deck) {
            card.printCard();
        }

    }
}