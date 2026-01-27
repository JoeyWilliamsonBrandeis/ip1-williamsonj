package edu.brandeis.cosi103a.ip1;

public abstract class Card {
    private int cost;
    private int value;

    protected Card(int cost, int value) {
        this.cost = cost;
        this.value = value;
    }

    public int getCost() { return cost; }
    public int getValue() { return value; }

    public boolean isAutomation() { return false; }
    public boolean isCrypto() { return false; }

    public int getAPValue() { return 0; }
    public int getCoinValue() { return 0; }
}