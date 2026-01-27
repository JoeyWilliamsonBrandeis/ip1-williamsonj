package edu.brandeis.cosi103a.ip1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        long seed = System.currentTimeMillis();
        Game g = new Game(seed);
        g.play();
    }
}
