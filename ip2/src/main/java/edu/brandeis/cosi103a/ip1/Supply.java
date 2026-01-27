package edu.brandeis.cosi103a.ip1;

import java.util.HashMap;
import java.util.Map;

public class Supply {
    private final Map<String, Integer> counts = new HashMap<>();

    public static final String METHOD = "Method";
    public static final String MODULE = "Module";
    public static final String FRAMEWORK = "Framework";
    public static final String BITCOIN = "Bitcoin";
    public static final String ETHEREUM = "Ethereum";
    public static final String DOGECOIN = "Dogecoin";

    public Supply() {
        counts.put(METHOD, 14);
        counts.put(MODULE, 8);
        counts.put(FRAMEWORK, 8);
        counts.put(BITCOIN, 60);
        counts.put(ETHEREUM, 40);
        counts.put(DOGECOIN, 30);
    }

    public synchronized Card take(String name) {
        Integer c = counts.getOrDefault(name, 0);
        if (c <= 0) return null;
        counts.put(name, c - 1);
        return createCard(name);
    }

    private Card createCard(String name) {
        switch (name) {
            case METHOD: return new AutomationCard(2, 1);
            case MODULE: return new AutomationCard(5, 3);
            case FRAMEWORK: return new AutomationCard(8, 6);
            case BITCOIN: return new CryptoCard(0, 1);
            case ETHEREUM: return new CryptoCard(3, 2);
            case DOGECOIN: return new CryptoCard(6, 3);
            default: return null;
        }
    }

    public int count(String name) { return counts.getOrDefault(name, 0); }

    public boolean frameworksEmpty() { return count(FRAMEWORK) == 0; }

    @Override
    public String toString() {
        return "Supply{" + counts.toString() + "}";
    }
}
