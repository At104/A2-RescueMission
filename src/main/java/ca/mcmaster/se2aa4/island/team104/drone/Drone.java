package ca.mcmaster.se2aa4.island.team104.drone;

import ca.mcmaster.se2aa4.island.team104.actions.Action;

import ca.mcmaster.se2aa4.island.team104.actions.ActionFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Drone {
    protected Battery battery;
    protected Direction direction;
    protected CoordinateSystem coordinates;
    private final Logger logger = LogManager.getLogger();
    private ActionFactory actionFactory;

    public Drone(int batteryLevel, String direction, int x, int y) {
        this.battery = new Battery(batteryLevel);
        this.direction = Direction.directionFromString(direction);
        this.coordinates = new CoordinateSystem(x, y, Direction.directionFromString(direction));
    }


    public Direction getDirection() {
        return direction;
    }


    // add dynamic polymorphism for different actions and change battery level and direction based on that
    public boolean decreaseBatteryOfAction(ActionResult result) {
        int cost = result.getCost();
        if (battery.hasEnoughCharge(cost)) {
            battery.decreaseBattery(cost);
            return true;
        } 
        else {
            logger.info("Battery too low! Stopping mission.");
            return false;
        }
    }

    private void setDirection(String direction) {
        this.direction = Direction.directionFromString(direction);
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

    public Action runEchoAction(Direction heading) {
        return actionFactory.createEchoAction(heading);
    }



}
