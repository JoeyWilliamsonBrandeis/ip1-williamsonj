package edu.brandeis.cosi103a.ip1;

/**
 * Represents a player in the dice game
 */
public class Player {
    private String name;
    private int score;
    private int turnsCompleted;
    
    public Player(String name) {
        this.name = name != null && !name.isEmpty() ? name : "Player";
        this.score = 0;
        this.turnsCompleted = 0;
    }
    
    /**
     * Gets the player's name
     * @return the player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the player's current score
     * @return the current score
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Adds points to the player's score
     * @param points the points to add
     */
    public void addScore(int points) {
        score += points;
    }
    
    /**
     * Gets the number of turns completed
     * @return the number of turns completed
     */
    public int getTurnsCompleted() {
        return turnsCompleted;
    }
    
    /**
     * Increments the number of turns completed
     */
    public void completeTurn() {
        turnsCompleted++;
    }
    
    /**
     * Resets the player's score and turns for a new game
     */
    public void reset() {
        score = 0;
        turnsCompleted = 0;
    }
}
