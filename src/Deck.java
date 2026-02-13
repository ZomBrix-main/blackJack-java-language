import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    List<Card> deck = new ArrayList<>();

    public Deck() {
        deck.add(new Card("kier", "A"));
        deck.add(new Card("kier", "2"));
        deck.add(new Card("kier", "3"));
        deck.add(new Card("kier", "4"));
        deck.add(new Card("kier", "5"));
        deck.add(new Card("kier", "6"));
        deck.add(new Card("kier", "7"));
        deck.add(new Card("kier", "8"));
        deck.add(new Card("kier", "9"));
        deck.add(new Card("kier", "10"));
        deck.add(new Card("kier", "J"));
        deck.add(new Card("kier", "Q"));
        deck.add(new Card("kier", "K"));

        deck.add(new Card("pik", "3"));
        deck.add(new Card("pik", "2"));
        deck.add(new Card("pik", "A"));
        deck.add(new Card("pik", "4"));
        deck.add(new Card("pik", "5"));
        deck.add(new Card("pik", "6"));
        deck.add(new Card("pik", "7"));
        deck.add(new Card("pik", "8"));
        deck.add(new Card("pik", "9"));
        deck.add(new Card("pik", "10"));
        deck.add(new Card("pik", "J"));
        deck.add(new Card("pik", "Q"));
        deck.add(new Card("pik", "K"));

        deck.add(new Card("trefl", "A"));
        deck.add(new Card("trefl", "2"));
        deck.add(new Card("trefl", "3"));
        deck.add(new Card("trefl", "4"));
        deck.add(new Card("trefl", "5"));
        deck.add(new Card("trefl", "6"));
        deck.add(new Card("trefl", "7"));
        deck.add(new Card("trefl", "8"));
        deck.add(new Card("trefl", "9"));
        deck.add(new Card("trefl", "10"));
        deck.add(new Card("trefl", "J"));
        deck.add(new Card("trefl", "Q"));
        deck.add(new Card("trefl", "K"));

        deck.add(new Card("karo", "A"));
        deck.add(new Card("karo", "2"));
        deck.add(new Card("karo", "3"));
        deck.add(new Card("karo", "4"));
        deck.add(new Card("karo", "5"));
        deck.add(new Card("karo", "6"));
        deck.add(new Card("karo", "7"));
        deck.add(new Card("karo", "8"));
        deck.add(new Card("karo", "9"));
        deck.add(new Card("karo", "10"));
        deck.add(new Card("karo", "J"));
        deck.add(new Card("karo", "Q"));
        deck.add(new Card("karo", "K"));
    }

    public List<Card> getDeck() {
        return deck;
    }

//    public List<Card> shuffleDeck() {
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        Card drawedCard = deck.getLast();
        deck.removeLast();

        return drawedCard;
    }

    public boolean quarterLeft() {
        return deck.size() < 13;
    }

//TIP Własna funkcja na tasowanie do celów naukowych
    public List<Card> customShuffle(int liczbaZamian) {
        if (liczbaZamian > 0) {
            Random random = new Random();
            int index1 = random.nextInt(deck.size());
            int index2 = random.nextInt(deck.size());
            Card tempCard = deck.get(index1);
            deck.set(index1, deck.get(index2));
            deck.set(index2, tempCard);
            customShuffle(liczbaZamian - 1);
        }

        return deck;
    }
}
