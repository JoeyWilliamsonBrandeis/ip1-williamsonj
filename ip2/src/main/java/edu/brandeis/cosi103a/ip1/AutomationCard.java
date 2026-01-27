package edu.brandeis.cosi103a.ip1;

public class AutomationCard extends Card {
    private final int apValue;

    public AutomationCard(int cost, int apValue) {
        super(cost, 0);
        this.apValue = apValue;
    }

    @Override
    public boolean isAutomation() { return true; }

    @Override
    public int getAPValue() { return apValue; }

    @Override
    public String toString() { return "Automation(cost=" + getCost() + " ap=" + apValue + ")"; }
}
