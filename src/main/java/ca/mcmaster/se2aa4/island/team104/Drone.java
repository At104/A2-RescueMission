package ca.mcmaster.se2aa4.island.team104;

import ca.mcmaster.se2aa4.island.team104.actions.Action;

public class Drone {
    protected Battery battery;
    protected Direction direction;
    protected CoordinateSystem coordinates;

    public Drone(int batteryLevel, String direction, int x, int y) {
        this.battery = new Battery(batteryLevel);
        this.direction = Direction.directionFromString(direction);
        this.coordinates = new CoordinateSystem(x, y, Direction.directionFromString(direction));
    }


    public Direction getDirection() {
        return direction;
    }


    // add dynamic polymorphism for different actions and change battery level and direction based on that
    public void changeFromAction(Action action) {
        return;
    }

    private void setDirection(String direction) {
        this.direction = Direction.directionFromString(direction);
    }

}
