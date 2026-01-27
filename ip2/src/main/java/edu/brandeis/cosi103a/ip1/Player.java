package edu.brandeis.cosi103a.ip1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Player {
    private final String name;
    private final List<Card> draw = new ArrayList<>();
    private final List<Card> discard = new ArrayList<>();
    private final List<Card> hand = new ArrayList<>();
    private final List<Card> played = new ArrayList<>();
    private final Random rand = new Random();

    public Player(String name, Supply supply, Random seed) {
        this.name = name;
        // starter deck: 7 Bitcoins and 3 Methods
        for (int i = 0; i < 7; i++) {
            Card c = supply.take(Supply.BITCOIN);
            if (c != null) draw.add(c);
        }
        for (int i = 0; i < 3; i++) {
            Card c = supply.take(Supply.METHOD);
            if (c != null) draw.add(c);
        }
        Collections.shuffle(draw, seed);
        // draw initial hand
        drawHand();
    }

    private void ensureDrawNotEmpty() {
        if (draw.isEmpty() && !discard.isEmpty()) {
            draw.addAll(discard);
            discard.clear();
            Collections.shuffle(draw, rand);
        }
    }

    public void drawHand() {
        while (hand.size() < 5) {
            ensureDrawNotEmpty();
            if (draw.isEmpty()) break;
            hand.add(draw.remove(draw.size() - 1));
        }
    }

    public int playAllCrypto() {
        int coins = 0;
        Iterator<Card> it = new ArrayList<>(hand).iterator();
        while (it.hasNext()) {
            Card c = it.next();
            if (c.isCrypto()) {
                coins += c.getCoinValue();
                hand.remove(c);
                played.add(c);
            }
        }
        return coins;
    }

    public String buyWithStrategy(Supply supply, int coins) {
        // prefer automation (Framework > Module > Method)
        if (coins >= 8) {
            Card bought = supply.take(Supply.FRAMEWORK);
            if (bought != null) { discard.add(bought); return Supply.FRAMEWORK; }
        }
        if (coins >= 5) {
            Card bought = supply.take(Supply.MODULE);
            if (bought != null) { discard.add(bought); return Supply.MODULE; }
        }
        if (coins >= 2) {
            Card bought = supply.take(Supply.METHOD);
            if (bought != null) { discard.add(bought); return Supply.METHOD; }
        }
        // otherwise buy best crypto affordable (Dogecoin > Ethereum > Bitcoin)
        if (coins >= 6) {
            Card bought = supply.take(Supply.DOGECOIN);
            if (bought != null) { discard.add(bought); return Supply.DOGECOIN; }
        }
        if (coins >= 3) {
            Card bought = supply.take(Supply.ETHEREUM);
            if (bought != null) { discard.add(bought); return Supply.ETHEREUM; }
        }
        // Bitcoin costs 0; attempt to buy if present
        Card bought = supply.take(Supply.BITCOIN);
        if (bought != null) { discard.add(bought); return Supply.BITCOIN; }
        return null;
    }

    public void cleanup() {
        // discard hand and played
        discard.addAll(hand);
        hand.clear();
        discard.addAll(played);
        played.clear();
        // draw new hand
        drawHand();
    }

    public int totalAPs() {
        int sum = 0;
        for (Card c : allCards()) sum += c.getAPValue();
        return sum;
    }

    public List<Card> allCards() {
        List<Card> all = new ArrayList<>();
        all.addAll(draw);
        all.addAll(discard);
        all.addAll(hand);
        all.addAll(played);
        return all;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name + " AP=" + totalAPs() + " cards=" + allCards().size(); }
}
