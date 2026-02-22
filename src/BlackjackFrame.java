import javax.swing.*;
import java.awt.*;

public class BlackjackFrame extends JFrame {
    private Game game;

    private JLabel playerNameLabel;
    private JLabel balanceLabel;
    private JLabel betLabel;
    private JTextArea playerHandArea;
    private JTextArea dealerHandArea;
    private JLabel statusLabel;

    private JButton hitButton;
    private JButton standButton;
    private JButton doubleButton;
    private JButton splitButton;
    private JButton newRoundButton;

    public BlackjackFrame() {
        super("Blackjack");

        this.game = new Game();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        initComponents();

        game.initializePhase();
        game.mainPhase();

        setVisible(true);
    }

    private void initComponents() {
        // Główny panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        setContentPane(mainPanel);

        // Panel górny - informacje o graczu i zakładzie
        JPanel topPanel = new JPanel(new GridLayout(1, 3));
        playerNameLabel = new JLabel("Gracz: " /* + game.getPlayerName() w przyszłości */);
        balanceLabel = new JLabel("Saldo: ");
        betLabel = new JLabel("Zakład: ");

        topPanel.add(playerNameLabel);
        topPanel.add(balanceLabel);
        topPanel.add(betLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        //Panel środkowy - ręka gracza i dealera
        JPanel centerPanel = new JPanel(new GridLayout(1, 2));

        playerHandArea = new JTextArea();
        playerHandArea.setEditable(false);
        playerHandArea.setBorder(BorderFactory.createTitledBorder("Twoje karty"));

        dealerHandArea = new JTextArea();
        dealerHandArea.setEditable(false);
        dealerHandArea.setBorder(BorderFactory.createTitledBorder("Karty dealera"));

        centerPanel.add(new JScrollPane(playerHandArea));
        centerPanel.add(new JScrollPane(dealerHandArea));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        //Panel dolny - przyciski akcji
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        doubleButton = new JButton("Double");
        splitButton = new JButton("Split");
        newRoundButton = new JButton("Nowa runda");

        buttonsPanel.add(hitButton);
        buttonsPanel.add(standButton);
        buttonsPanel.add(doubleButton);
        buttonsPanel.add(splitButton);
        buttonsPanel.add(newRoundButton);

        bottomPanel.add(buttonsPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Status gry.");

        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        attachListeners();
    }

    private void attachListeners() {
        //hitButton.addActionListener(e -> onHit());
    }

    private void onHit() {
        //gracz akcja hit + aktualizacja gui
    }
}
