import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //DRAFT
//        Deck deck = new Deck();
//        System.out.println("Talia niepotasowana:");
//        System.out.println();
//
//        for (Card card : deck.getDeck()) {
//            card.printCard();
//        }
//        System.out.println("Talia potasowana:");
//        deck.shuffleDeck();
//
//        for (Card card : deck.getDeck()) {
//            card.printCard();
//        }
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        do {
            game.initializePhase();
            game.mainPhase();
            System.out.println("Czy chcesz zagrać ponownie? 1 - tak");
        } while (sc.nextInt() == 1);

        sc.close();
    }
}