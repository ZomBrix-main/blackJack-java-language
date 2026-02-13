import java.util.Scanner;

import static java.lang.Math.floor;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;

    public Game() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj nazwę gracza: ");
        String playerName = sc.next();

        this.deck = new Deck();
        this.deck.shuffleDeck();

        this.player = new Player(playerName);
        this.dealer = new Dealer();

        System.out.println("Gracz: " + player.getName());
        System.out.println("Saldo: " + player.getBalance());
    }

    //Deprecated
//    public void playerMoves(Scanner sc, Hand hand, Deck gameDeck, boolean canDouble, boolean canSplit) {
//        boolean stand = false;
//
//        while (!stand) {
//            String options = "1: HIT 2: STAND";
//            if (canDouble) options = options + " 3: DOUBLE DOWN";
//            if (canSplit) options = options + " 4: SPLIT";
//
//            System.out.println(options);
//
//            int option;
//            do {
//                System.out.println("Wybierz opcję: ");
//                while (!sc.hasNextInt()) {
//                    System.out.print("Błąd formatu, wybierz poprawną opcję: ");
//                    sc.next();
//                }
//                option = sc.nextInt();
//            } while ((option == 3 && !canDouble) || (option == 4 && !canSplit));
//        }
//    }

    public void splitSegment(Scanner sc) {
        int option;
        boolean bust = false;
        do {
            String options = "1: HIT 2: STAND";
            if (player.getSplitHand().handCount() == 2 && player.getBalance() >= player.getSplitBet()) {
                options = options + " 3: DOUBLE DOWN";
            }

            System.out.println(options);

            do {
                System.out.println("Wybierz opcję: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Błąd formatu, wybierz poprawną opcję: ");
                    sc.next();
                }
                option = sc.nextInt();
            } while ((option == 3 && !(player.getSplitHand().handCount() == 2 && player.getBalance() >= player.getSplitBet())));
            switch (option) {
                case 1:
                    player.drawSplitCard(deck);
                    break;
                case 2:
                    break;
                case 3:
                    player.setSplitBet(player.getSplitBet());
                    player.drawSplitCard(deck);
                    break;
            }

            if (player.calculatePlayerSplitHandValue() >= 21) {
                bust = true;
            }

            System.out.println("Twoje karty drugiej ręki:");
            player.printSplitHand();
            System.out.println("Wartość drugiej ręki:" + player.calculatePlayerSplitHandValue());
        } while (option != 2 && option != 3 && !bust);
    }

    public void initializePhase() {
        Scanner sc = new Scanner(System.in);
        int bet = 0;
        System.out.println("Wprowadź zakład: ");
        do {
            while (!sc.hasNextInt()) {
                System.out.print("Błąd formatu, podaj poprawny zakład: ");
                sc.next();
            }
            bet = sc.nextInt();
        } while (!player.checkBetAmmount(bet));
        player.setBet(bet);

        System.out.println("Gracz: " + player.getName());
        System.out.println("Saldo: " + player.getBalance());
        System.out.println("Zakład: " + player.getBet());
    }

    public void mainPhase() {
        if (deck.quarterLeft()) {
            deck = new Deck();
            this.deck.shuffleDeck();
        }

        player.initialHand(deck);
        dealer.drawUpcard(deck);
        player.drawCard(deck);
        dealer.drawHoleCard(deck);

        System.out.println("Karta dealera:");
        dealer.printUpcard();
        System.out.println("Twoje karty:");
        player.printHand();
        System.out.println("Wartość ręki:" + player.calculatePlayerHandValue());

        if (player.calculatePlayerHandValue() == 21 && dealer.calculateDealerHandValue() == 21) {
            System.out.println("Remis. Blackjack gracza i dealera. Pieniądze wracają. Karty dealera:");
            dealer.printHand();
            player.addBalance(player.getBet());
            player.resetBet();
            player.resetHand();
            dealer.resetHand();
        } else if (player.calculatePlayerHandValue() == 21 || dealer.calculateDealerHandValue() == 21) {
            if (player.calculatePlayerHandValue() == 21) {
                System.out.println("Blackjack! Wygrana:" + (int) floor(1.5 * player.getBet()));
                player.addBalance((int) floor(1.5 * player.getBet()));
            } else {
                System.out.println("Przegrana. Blackjack Dealera. Karty dealera:");
                dealer.printHand();
            }
            player.resetBet();
            player.resetHand();
            dealer.resetHand();
        } else if (player.calculatePlayerHandValue() > 21) {
            System.out.println("Przegrana. Przekroczono maksymalną wartość ręki.");
            player.resetBet();
            player.resetHand();
            dealer.resetHand();
        } else {
            Scanner sc = new Scanner(System.in);
            int option;
            boolean bust = false;
            boolean didSplit = false;
            do {
                String options = "1: HIT 2: STAND";
                if (player.getHand().handCount() == 2 && player.getBalance() >= player.getBet()) {
                    options = options + " 3: DOUBLE DOWN";
                }
                if (player.canSplit() && player.getBalance() >= player.getBet()) {
                    options = options + " 4: SPLIT";
                }

                System.out.println(options);

                do {
                    System.out.println("Wybierz opcję: ");
                    while (!sc.hasNextInt()) {
                        System.out.print("Błąd formatu, wybierz poprawną opcję: ");
                        sc.next();
                    }
                    option = sc.nextInt();
                } while ((option == 3 && !(player.getHand().handCount() == 2 && player.getBalance() >= player.getBet())) ||
                        (option == 4 && !(player.canSplit() && player.getBalance() >= player.getBet())));
                switch (option) {
                    case 1:
                        player.drawCard(deck);
                        break;
                    case 2:
                        break;
                    case 3:
                        player.setBet(player.getBet());
                        player.drawCard(deck);
                        break;
                    case 4:
                        player.setSplitBet(player.getBet());
                        player.splitOption(deck);
                        didSplit = true;
                        break;
                }

                if (player.calculatePlayerHandValue() >= 21) {
                    bust = true;
                }

                System.out.println("Twoje karty:");
                player.printHand();
                System.out.println("Wartość ręki:" + player.calculatePlayerHandValue());

            } while (!(option == 2) && !(option == 3) && !bust);

            if (didSplit) {
                splitSegment(sc);
            }

            while (dealer.calculateDealerHandValue() < 17 || dealer.softHand()) {
                dealer.drawCard(deck);
            }
            System.out.println("Karty dealera:");
            dealer.printHand();

            if (didSplit) System.out.println("Wynik pierwszej ręki:");

            if (player.calculatePlayerHandValue() > 21) {
                System.out.println("Przegrana. Przekroczono maksymalną wartość ręki.");
            } else if (dealer.calculateDealerHandValue() > 21) {
                System.out.println("Wygrana:" + (2 * player.getBet()));
                player.addBalance((2 * player.getBet()));
            } else if (player.calculatePlayerHandValue() > dealer.calculateDealerHandValue()) {
                System.out.println("Wygrana:" + (2 * player.getBet()));
                player.addBalance((2 * player.getBet()));
            } else if (player.calculatePlayerHandValue() < dealer.calculateDealerHandValue()) {
                System.out.println("Przegrana. Wartość ręki mniejsza niż dealera.");
            } else System.out.println("Dziwny przypadek");

            player.resetBet();
            player.resetHand();

            if (didSplit) {
                System.out.println("Wynik drugiej ręki:");

                if (player.calculatePlayerSplitHandValue() > 21) {
                    System.out.println("Przegrana. Przekroczono maksymalną wartość ręki.");
                } else if (dealer.calculateDealerHandValue() > 21) {
                    System.out.println("Wygrana:" + (2 * player.getSplitBet()));
                    player.addBalance((2 * player.getSplitBet()));
                }  else if (player.calculatePlayerSplitHandValue() > dealer.calculateDealerHandValue()) {
                    System.out.println("Wygrana:" + (2 * player.getSplitBet()));
                    player.addBalance((2 * player.getSplitBet()));
                } else if (player.calculatePlayerSplitHandValue() < dealer.calculateDealerHandValue()) {
                    System.out.println("Przegrana. Wartość ręki mniejsza niż dealera.");
                } else System.out.println("Dziwny przypadek");

                player.resetSplitBet();
                player.resetSplitHand();
            }
            dealer.resetHand();

            System.out.println("Gracz: " + player.getName());
            System.out.println("Saldo: " + player.getBalance());
            System.out.println("Zakład: " + player.getBet());
        }
    }
}
