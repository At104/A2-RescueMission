package ca.mcmaster.se2aa4.island.team104;

import ca.mcmaster.se2aa4.island.team104.actions.Action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone {
    protected Battery battery;
    protected Direction direction;
    protected CoordinateSystem coordinates;
    private final Logger logger = LogManager.getLogger();

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
        int cost = action.getCost();
        if (battery.hasEnoughCharge(cost)) {
            battery.decreaseBattery(cost);
        } 
        else {
            logger.info("Battery too low! Stopping mission.");
        }
    }

    private void setDirection(String direction) {
        this.direction = Direction.directionFromString(direction);
    }

}
