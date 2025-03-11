package ca.mcmaster.se2aa4.island.teamXXX;

public class Battery {

    private int battery;

    public Battery(int battery) {
        this.battery = battery;
    }

    public int getBatteryLevel() {
        return battery;
    }

    public void decreaseBattery(int cost) {
        battery = battery - cost;
    }

}
