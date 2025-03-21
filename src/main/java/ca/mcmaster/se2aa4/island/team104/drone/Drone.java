package ca.mcmaster.se2aa4.island.team104.drone;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.ActionFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone {
    private Position position;
    private Direction heading;
    private int batteryLevel;
    private final CoordinateMap map;
    private final Logger logger = LogManager.getLogger();
    private final ActionFactory actionFactory;

    public Drone(Position startPos, Direction startDir, int battery, CoordinateMap map) {
        this.position = startPos;
        this.heading = startDir;
        this.batteryLevel = battery;
        this.map = map;
        this.actionFactory = new ActionFactory();
    }

    public Position getPosition() {
        return position;
    }

    public Direction getHeading() {
        return heading;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean consumeBattery(int amount) {
        if (batteryLevel >= amount) {
            batteryLevel -= amount;
            return true;
        }
        return false;
    }

    public boolean hasEnoughBattery(int requiredAmount) {
        return batteryLevel >= requiredAmount;
    }

    public boolean isOutOfBattery() {
        return batteryLevel <= 0;
    }

    public boolean decreaseBatteryOfAction(ActionResult result) {
        int cost = result.getCost();
        if (batteryLevel >= cost) {
            batteryLevel -= cost;
            return true;
        } 
        else {
            logger.info("Battery too low! Stopping mission.");
            return false;
        }
    }

    public Action runScanAction() {
        return actionFactory.createScanAction();
    }

    public Action runFlyAction() {
        return actionFactory.createFlyAction();
    }

    public Action runStopAction() {
        return actionFactory.createStopAction();
    }

    public Action runHeadingAction(Direction direction) {
        return actionFactory.createHeadingAction(direction);
    }

    public Action runEchoAction(Direction direction) {
        return actionFactory.createEchoAction(direction);
    }

    public void updatePosition(Position newPosition) {
        this.position = newPosition;
        map.addVisitedPosition(newPosition);
    }

    public void updateHeading(Direction newHeading) {
        this.heading = newHeading;
    }

    public CoordinateMap getMap() {
        return map;
    }
}
