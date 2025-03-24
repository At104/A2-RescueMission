package ca.mcmaster.se2aa4.island.team104.drone;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.ActionFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.results.ScanActionResult;
import eu.ace_design.island.map.resources.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Drone {
    private Position position;
    private Direction heading;
    private int batteryLevel;
    private final CoordinateMap map;
    private final Logger logger = LogManager.getLogger();
    private final ActionFactory actionFactory;
    private ScanActionResult lastScanResult;

    public Drone(Position startPos, Direction startDir, int battery, CoordinateMap map) {
        logger.info("Initializing Drone...");
        logger.info("Starting Position: {}", startPos);
        logger.info("Starting Direction: {}", startDir);
        logger.info("Battery Level: {}", battery);
        logger.info("Coordinate Map: {}", map);

        this.position = startPos;
        this.heading = startDir;
        this.batteryLevel = battery;
        this.map = map;
        this.actionFactory = new ActionFactory();

        logger.info("Drone initialization complete.");
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

    public boolean isOutOfBattery() {
        return batteryLevel <= 0;
    }

    public void decreaseBatteryOfAction(ActionResult result) {
        int cost = result.getCost();
        if (batteryLevel >= cost) {
            batteryLevel -= cost;
            logger.info("Battery decreased by {}. Remaining battery: {}", cost, batteryLevel);
        } else {
            logger.warn("Battery too low! Stopping mission.");
        }
    }

    public Action runScanAction() {
        logger.info("Creating Scan Action...");
        return actionFactory.createScanAction();
    }

    public Action runFlyAction() {
        logger.info("Creating Fly Action...");
        return actionFactory.createFlyAction();
    }

    public Action runStopAction() {
        logger.info("Creating Stop Action...");
        return actionFactory.createStopAction();
    }

    public Action runHeadingAction(Direction direction) {
        logger.info("Creating Heading Action for direction: {}", direction);
        return actionFactory.createHeadingAction(direction);
    }

    public Action runEchoAction(Direction direction) {
        logger.info("Creating Echo Action for direction: {}", direction);
        return actionFactory.createEchoAction(direction);
    }

    public void updatePosition(Position newPosition) {
        logger.info("Updating position to: {}", newPosition);
        this.position = newPosition;
        map.addVisitedPosition(newPosition);
    }

    public void updateHeading(Direction newHeading) {
        logger.info("Updating heading to: {}", newHeading);
        this.heading = newHeading;
    }

    public CoordinateMap getMap() {
        return map;
    }

    public void processScanResult(ActionResult result) {
        logger.info("Processing scan result...");
        if (result.getScanResult() != null) {
            lastScanResult = result.getScanResult();
            
            for (String creek : lastScanResult.creeks()) {
                logger.info("Adding creek POI: {}", creek);
                map.addPointOfInterest(new PointOfInterest(creek, position, POIType.CREEK));
            }
            for (String site : lastScanResult.sites()) {
                logger.info("Found emergency site: {} at position: {}", site, position);
                map.addPointOfInterest(new PointOfInterest(site, position, POIType.EMERGENCY_SITE));
            }
        } 
        else {
            logger.warn("No scan result found in action result.");
        }
    }

    public List<String> getCurrentBiome() {
        return lastScanResult != null && !lastScanResult.biomes().isEmpty() ? lastScanResult.biomes() : null;
    }
}
