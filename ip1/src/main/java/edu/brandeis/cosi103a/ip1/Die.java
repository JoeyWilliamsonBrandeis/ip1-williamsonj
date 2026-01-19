package edu.brandeis.cosi103a.ip1;

import java.util.Random;

/**
 * Represents a 6-sided die
 */
public class Die {
    private static final int SIDES = 6;
    private Random random;
    
    public Die() {
        this.random = new Random();
    }
    
    /**
     * Rolls the die and returns a value between 1 and 6
     * @return the result of the die roll
     */
    public int roll() {
        return random.nextInt(SIDES) + 1;
    }
}
