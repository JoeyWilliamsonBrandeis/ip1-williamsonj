package edu.brandeis.cosi103a.ip1;

import java.util.Scanner;

/**
 * Main game logic for the dice game
 */
public class DiceGame {
    private static final int TURNS_PER_PLAYER = 10;
    private static final int MAX_REROLLS = 2;
    
    private Player player1;
    private Player player2;
    private Die die;
    private Scanner scanner;
    
    public DiceGame(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.die = new Die();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Starts and runs the game
     */
    public void play() {
        displayWelcome();
        
        // Play all turns
        for (int turn = 1; turn <= TURNS_PER_PLAYER; turn++) {
            System.out.println("=== Turn " + turn + " ===");
            playPlayerTurn(player1);
            playPlayerTurn(player2);
            displayTurnSummary(turn);
        }
        
        // Determine and display winner
        displayGameOver();
    }
    
    /**
     * Plays a single turn for a player
     * @param player the player taking the turn
     */
    private void playPlayerTurn(Player player) {
        System.out.println(player.getName() + "'s turn:");
        
        int currentRoll = die.roll();
        System.out.println("  Die roll: " + currentRoll);
        
        // Allow re-rolls
        int rerollsUsed = 0;
        while (rerollsUsed < MAX_REROLLS) {
            System.out.print("  Re-roll? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                currentRoll = die.roll();
                System.out.println("  Die roll: " + currentRoll);
                rerollsUsed++;
            } else if (response.equals("no") || response.equals("n")) {
                break;
            } else {
                System.out.println("  Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        
        // Add score and complete turn
        player.addScore(currentRoll);
        player.completeTurn();
        System.out.println("  " + player.getName() + " ends turn with roll: " + currentRoll);
        System.out.println("  " + player.getName() + "'s current score: " + player.getScore() + "\n");
    }
    
    /**
     * Displays welcome message and game rules
     */
    private void displayWelcome() {
        System.out.println("=== Welcome to the Dice Game ===\n");
        System.out.println("Players: " + player1.getName() + " vs " + player2.getName());
        System.out.println("Each player will take " + TURNS_PER_PLAYER + " turns.");
        System.out.println("On each turn, you'll roll a die and can re-roll up to " + MAX_REROLLS + " times.");
        System.out.println("After choosing to end your turn, your die value is added to your score.");
        System.out.println("The player with the highest score wins!\n");
    }
    
    /**
     * Displays score summary after each turn
     * @param turn the turn number
     */
    private void displayTurnSummary(int turn) {
        System.out.println("Scores after turn " + turn + ": " 
            + player1.getName() + " = " + player1.getScore() 
            + ", " + player2.getName() + " = " + player2.getScore() + "\n");
    }
    
    /**
     * Displays game over message and determines the winner
     */
    private void displayGameOver() {
        System.out.println("=== Game Over ===");
        System.out.println(player1.getName() + " final score: " + player1.getScore());
        System.out.println(player2.getName() + " final score: " + player2.getScore());
        System.out.println();
        
        determineWinner();
        scanner.close();
    }
    
    /**
     * Determines and announces the winner
     */
    private void determineWinner() {
        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " wins!");
        } else if (player2.getScore() > player1.getScore()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
