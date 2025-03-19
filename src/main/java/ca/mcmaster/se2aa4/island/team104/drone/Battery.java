package ca.mcmaster.se2aa4.island.team104.drone;

public class Battery {

    private int battery;
    private int maxCapacity;

    public Battery(int battery) {
        this.battery = maxCapacity; //start off at max capacity
        this.maxCapacity = battery;
    }

    public int getBatteryLevel() {
        return battery;
    }

    public boolean hasEnoughCharge(int cost) {
        return battery >= cost;
    }

    public void decreaseBattery(int cost) {
        if (cost > 0 && hasEnoughCharge(cost)) {
            battery -= cost;
        }
    }

    public void recharge(int amount) {
        battery = Math.min(maxCapacity, battery + amount);
    }

}
