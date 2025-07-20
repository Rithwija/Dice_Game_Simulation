package src.Dice_Game_Simulation;

public class Player{
    private String name;
    private int wins;
    private int lastRoll;
    public Player(String name){
        this.name = name;
    }
    public int showWins(){
        return this.wins;
    }
    public String showName(){
        return this.name;
    }
    public void incrementWin(){
        this.wins++;
    }
    public void setLastRoll(int roll) {
        this.lastRoll = roll;
    }
    public int getLastRoll() {
        return this.lastRoll;
    }
}