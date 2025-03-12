package ca.mcmaster.se2aa4.island.team104;

import ca.mcmaster.se2aa4.island.team104.actions.Action;

public class Drone {
    protected Battery battery;
    protected String direction;

    public Drone(int batteryLevel, String direction) {
        this.battery = new Battery(batteryLevel);
        this.direction = direction;
    }


    public String getDirection() {
        return direction;
    }


    // add dynamic polymorphism for different actions and change battery level and direction based on that
    public void changeFromAction(Action action) {
        return;
    }

    private void setDirection(String direction) {
        this.direction = direction;
    }

}
