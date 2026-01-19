package edu.brandeis.cosi103a.ip1;

import java.util.Scanner;

/**
 * Dice Game Entry Point
 * A two-player dice game where players take turns rolling a die
 * and accumulating points over 10 turns each.
 */
public class App 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get player names
        System.out.print("Enter Player 1 name: ");
        String player1Name = scanner.nextLine().trim();
        
        System.out.print("Enter Player 2 name: ");
        String player2Name = scanner.nextLine().trim();
        
        scanner.close();
        
        // Create and start the game
        DiceGame game = new DiceGame(player1Name, player2Name);
        game.play();
    }
}
