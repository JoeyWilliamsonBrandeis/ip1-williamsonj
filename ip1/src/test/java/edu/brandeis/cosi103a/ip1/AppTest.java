package edu.brandeis.cosi103a.ip1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

/**
 * Unit tests for the Dice Game
 */
public class AppTest 
{
    private Die die;
    private Player player1;
    private Player player2;
    
    @Before
    public void setUp() {
        die = new Die();
        player1 = new Player("Alice");
        player2 = new Player("Bob");
    }
    
    // ===== Die Tests =====
    
    @Test
    public void testDieRollIsInValidRange() {
        for (int i = 0; i < 100; i++) {
            int roll = die.roll();
            assertTrue("Die roll should be between 1 and 6", roll >= 1 && roll <= 6);
        }
    }
    
    @Test
    public void testDieCanRollOne() {
        boolean rolledOne = false;
        for (int i = 0; i < 1000; i++) {
            if (die.roll() == 1) {
                rolledOne = true;
                break;
            }
        }
        assertTrue("Die should be able to roll a 1", rolledOne);
    }
    
    @Test
    public void testDieCanRollSix() {
        boolean rolledSix = false;
        for (int i = 0; i < 1000; i++) {
            if (die.roll() == 6) {
                rolledSix = true;
                break;
            }
        }
        assertTrue("Die should be able to roll a 6", rolledSix);
    }
    
    // ===== Player Tests =====
    
    @Test
    public void testPlayerInitializationWithName() {
        assertEquals("Player name should be 'Alice'", "Alice", player1.getName());
        assertEquals("Player name should be 'Bob'", "Bob", player2.getName());
    }
    
    @Test
    public void testPlayerInitializationWithEmptyName() {
        Player playerWithEmpty = new Player("");
        assertEquals("Empty name should default to 'Player'", "Player", playerWithEmpty.getName());
    }
    
    @Test
    public void testPlayerInitialScoreIsZero() {
        assertEquals("Initial score should be 0", 0, player1.getScore());
        assertEquals("Initial score should be 0", 0, player2.getScore());
    }
    
    @Test
    public void testPlayerAddScore() {
        player1.addScore(5);
        assertEquals("Score should be 5 after adding 5", 5, player1.getScore());
        
        player1.addScore(3);
        assertEquals("Score should be 8 after adding 3 more", 8, player1.getScore());
    }
    
    @Test
    public void testPlayerAddMultipleScores() {
        player1.addScore(4);
        player1.addScore(6);
        player1.addScore(2);
        assertEquals("Score should be 12 after adding 4, 6, and 2", 12, player1.getScore());
    }
    
    @Test
    public void testPlayerTurnsCompleted() {
        assertEquals("Initial turns completed should be 0", 0, player1.getTurnsCompleted());
        
        player1.completeTurn();
        assertEquals("Turns completed should be 1", 1, player1.getTurnsCompleted());
        
        player1.completeTurn();
        assertEquals("Turns completed should be 2", 2, player1.getTurnsCompleted());
    }
    
    @Test
    public void testPlayerReset() {
        player1.addScore(25);
        player1.completeTurn();
        player1.completeTurn();
        
        player1.reset();
        
        assertEquals("Score should be 0 after reset", 0, player1.getScore());
        assertEquals("Turns should be 0 after reset", 0, player1.getTurnsCompleted());
    }
    
    // ===== DiceGame Tests =====
    
    @Test
    public void testDiceGameInitialization() {
        DiceGame game = new DiceGame("Player1", "Player2");
        assertNotNull("Game should be created", game);
    }
    
    @Test
    public void testDiceGameWithEmptyNames() {
        DiceGame game = new DiceGame("", "");
        assertNotNull("Game should handle empty names", game);
    }
    
    @Test
    public void testPlayerCanHaveMultiplePlayers() {
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        Player p3 = new Player("Charlie");
        
        p1.addScore(10);
        p2.addScore(15);
        p3.addScore(12);
        
        assertTrue("Bob should have the highest score", 
            p2.getScore() > p1.getScore() && p2.getScore() > p3.getScore());
    }
    
    @Test
    public void testGameLogicCanTrack10Turns() {
        Player p1 = new Player("Alice");
        Player p2 = new Player("Bob");
        
        for (int i = 0; i < 10; i++) {
            p1.completeTurn();
            p2.completeTurn();
        }
        
        assertEquals("Player 1 should have completed 10 turns", 10, p1.getTurnsCompleted());
        assertEquals("Player 2 should have completed 10 turns", 10, p2.getTurnsCompleted());
    }
    
    @Test
    public void testScoreAccumulationWith10Turns() {
        Player p1 = new Player("Alice");
        
        // Simulate 10 turns with various rolls
        int[] rolls = {4, 6, 2, 5, 3, 1, 6, 4, 5, 2};
        int expectedTotal = 0;
        
        for (int roll : rolls) {
            p1.addScore(roll);
            expectedTotal += roll;
        }
        
        assertEquals("Score should equal sum of all rolls", expectedTotal, p1.getScore());
    }
    
    @Test
    public void testWinnerDetermination() {
        Player winner = new Player("Winner");
        Player loser = new Player("Loser");
        
        winner.addScore(50);
        loser.addScore(30);
        
        assertTrue("Winner should have higher score", winner.getScore() > loser.getScore());
    }
    
    @Test
    public void testTieScenario() {
        Player p1 = new Player("Player1");
        Player p2 = new Player("Player2");
        
        p1.addScore(40);
        p2.addScore(40);
        
        assertEquals("Scores should be equal for a tie", p1.getScore(), p2.getScore());
    }
}
