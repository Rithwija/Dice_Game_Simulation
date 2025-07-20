package src.Dice_Game_Simulation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameUI {

    private JFrame frame;
    private JTextField numPlayersField;
    private JPanel playersPanel;
    private JButton startButton, playButton;
    private JTextArea outputArea;
    private ArrayList<JTextField> playerNameFields;
    private ArrayList<Player> players;
    private GameEngine gameEngine;

    public GameUI() {
        frame = new JFrame("🎲 Dice Game Simulation");
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel numPlayersLabel = new JLabel("Enter number of players:");
        numPlayersLabel.setBounds(20, 20, 200, 30);
        frame.add(numPlayersLabel);

        numPlayersField = new JTextField();
        numPlayersField.setBounds(220, 20, 100, 30);
        frame.add(numPlayersField);

        JButton addPlayersButton = new JButton("Set Players");
        addPlayersButton.setBounds(340, 20, 120, 30);
        frame.add(addPlayersButton);

        playersPanel = new JPanel();
        playersPanel.setLayout(new GridLayout(0, 1));
        JScrollPane playerScroll = new JScrollPane(playersPanel);
        playerScroll.setBounds(20, 60, 550, 100);
        frame.add(playerScroll);

        startButton = new JButton("Start Game");
        startButton.setBounds(20, 170, 120, 30);
        startButton.setEnabled(false);
        frame.add(startButton);

        playButton = new JButton("Play Round");
        playButton.setBounds(160, 170, 120, 30);
        playButton.setEnabled(false);
        frame.add(playButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBounds(20, 220, 550, 220);
        frame.add(outputScroll);

        playerNameFields = new ArrayList<>();

        // Actions
        addPlayersButton.addActionListener(e -> {
            playersPanel.removeAll();
            playerNameFields.clear();
            int count;
            try {
                count = Integer.parseInt(numPlayersField.getText().trim());
                if (count <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number > 0.");
                return;
            }

            for (int i = 0; i < count; i++) {
                JTextField tf = new JTextField("Player " + (i + 1));
                playerNameFields.add(tf);
                playersPanel.add(tf);
            }
            playersPanel.revalidate();
            playersPanel.repaint();
            startButton.setEnabled(true);
        });

        startButton.addActionListener(e -> {
            players = new ArrayList<>();
            for (JTextField tf : playerNameFields) {
                players.add(new Player(tf.getText().trim()));
            }
            gameEngine = new GameEngine(players);
            outputArea.setText("🎮 Game started with " + players.size() + " players.\n");
            playButton.setEnabled(true);
        });

        playButton.addActionListener(e -> {
            if (gameEngine != null) {
                gameEngine.playRound();
                outputArea.append("\n➡️ New Round:\n" + gameEngine.getResults());
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameUI();
    }
}
