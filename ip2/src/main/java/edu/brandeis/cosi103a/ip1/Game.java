package edu.brandeis.cosi103a.ip1;

import java.util.Random;

public class Game {
    private final Supply supply = new Supply();
    private final Player[] players = new Player[2];
    private final Random rand;

    public Game(long seed) {
        this.rand = new Random(seed);
        players[0] = new Player("P1", supply, new Random(rand.nextLong()));
        players[1] = new Player("P2", supply, new Random(rand.nextLong()));
    }

    public void play() {
        int current = rand.nextInt(2);
        System.out.println("Starting player: " + players[current].getName());
        int turn = 0;
        while (!supply.frameworksEmpty()) { //Runs while there are frameworks in supply
            turn++;
            Player p = players[current];
            int coins = p.playAllCrypto();
            String bought = p.buyWithStrategy(supply, coins);
            if (bought != null) {
                System.out.println("Turn " + turn + ": " + p.getName() + " bought " + bought + " (coins=" + coins + ")");
            } else {
                System.out.println("Turn " + turn + ": " + p.getName() + " bought nothing (coins=" + coins + ")");
            }
            // check end condition immediately after a purchase
            if (supply.frameworksEmpty()) break;
            p.cleanup();
            current = 1 - current;
        }

        // game ended; compute APs
        System.out.println("Game ended after " + turn + " turns.");
        for (Player pl : players) {
            System.out.println(pl.getName() + " total APs=" + pl.totalAPs());
        }
        int ap0 = players[0].totalAPs();
        int ap1 = players[1].totalAPs();
        if (ap0 > ap1) System.out.println("Winner: " + players[0].getName());
        else if (ap1 > ap0) System.out.println("Winner: " + players[1].getName());
        else System.out.println("Tie");
    }
    
    
    
}
