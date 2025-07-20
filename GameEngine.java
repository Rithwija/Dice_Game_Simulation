package src.Dice_Game_Simulation;

import java.util.ArrayList;

public class GameEngine {
    private ArrayList<Player> arr;
    private Dice dice;

    public GameEngine(ArrayList<Player> list) {
        arr = list;
        dice = new Dice();
    }

    // Play one round: roll dice for each player and determine winner
    public void playRound() {
        int highestOp = 0;
        Player winPlayer = null;

        for (Player p : arr) {
            int n = dice.roll();
            p.setLastRoll(n); // Save roll for Swing UI display
            if (n > highestOp) {
                highestOp = n;
                winPlayer = p;
            } else if (n == highestOp) {
                winPlayer = null; // It's a tie
            }
        }

        if (winPlayer != null) {
            winPlayer.incrementWin();
        }
    }

    // Console-only method to display round result (optional if not using Execute.java)
    public void showResults() {
        for (Player p : arr) {
            System.out.println(p.showName() + " has won " + p.showWins() + " rounds");
        }
    }

    // Method used in Swing UI to generate round summary
    public String getResults() {
        StringBuilder sb = new StringBuilder();

        // Show what each player rolled
        for (Player p : arr) {
            sb.append(p.showName()).append(" rolled ").append(p.getLastRoll()).append("\n");
        }

        // Determine the max roll
        int maxRoll = arr.stream().mapToInt(Player::getLastRoll).max().orElse(0);

        // Find winners (in case of tie)
        ArrayList<Player> winners = new ArrayList<>();
        for (Player p : arr) {
            if (p.getLastRoll() == maxRoll) {
                winners.add(p);
            }
        }

        if (winners.size() == 1) {
            sb.append("\n🎉 Winner: ").append(winners.get(0).showName()).append("\n");
        } else {
            sb.append("\n🤝 It's a tie between: ");
            for (Player p : winners) {
                sb.append(p.showName()).append(" ");
            }
            sb.append("\n");
        }

        // Show updated scoreboard
        sb.append("\n📊 Current Scores:\n");
        for (Player p : arr) {
            sb.append(p.showName()).append(" → ").append(p.showWins()).append(" wins\n");
        }

        return sb.toString();
    }
}
