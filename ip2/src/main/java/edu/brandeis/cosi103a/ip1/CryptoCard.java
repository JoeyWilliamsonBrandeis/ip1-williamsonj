package edu.brandeis.cosi103a.ip1;

public class CryptoCard extends Card {
    private final int coinValue;

    public CryptoCard(int cost, int coinValue) {
        super(cost, 0);
        this.coinValue = coinValue;
    }

    @Override
    public boolean isCrypto() { return true; }

    @Override
    public int getCoinValue() { return coinValue; }

    @Override
    public String toString() { return "Crypto(cost=" + getCost() + " coin=" + coinValue + ")"; }
}
