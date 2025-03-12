package ca.mcmaster.se2aa4.island.teamXXX;

public class Battery {
    private int maxCapacity;
    private int currentLevel;

    public Battery(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentLevel = maxCapacity;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean hasEnoughCharge(int cost) {
        return currentLevel >= cost;
    }

    public void drain(int cost) {
        if (cost > 0 && hasEnoughCharge(cost)) {
            currentLevel -= cost;
        }
    }

    public void recharge(int amount) {
        currentLevel = Math.min(maxCapacity, currentLevel + amount);
    }
}