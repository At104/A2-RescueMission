package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.ActionType;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchStateSecond extends State {

    private Action action;
    private boolean changingDirection;
    private final Logger logger = LogManager.getLogger();

    public SearchStateSecond(Drone drone) {
        super(drone);
        action = drone.runFlyAction();
        this.changingDirection = false;
    }
    @Override
    public State getNextState(ActionResult result) {

        Drone drone = getDrone();
        action.execute(drone, result);
        Position position = drone.getPosition();
        Direction direction = drone.getHeading();
        CoordinateMap map = drone.getMap();

        logger.info(map.getCreeks().toString());

        if (drone.getBatteryLevel() < 35) {
            return new EndingState(drone);
        }

        if (action.type() == ActionType.FLY) {
            if (direction == Direction.NORTH) {
                if (position.getY() - 2 < 1 && position.getX() - 2 < 3 ) {
                    return new EndingState(drone);
                }
            }
            if (direction == Direction.SOUTH) {
                if (position.getY() + 2 >= map.getHeight() && position.getX() - 2 < 3) {
                    return new EndingState(drone);
                }
            }
        }

        // Continue moving
        return this;
    }

    @Override
    public Action getNextAction() {
        Drone drone = getDrone();
        Position position = drone.getPosition();
        CoordinateMap map = drone.getMap();
        Direction direction = drone.getHeading();

        if (action.type() == ActionType.FLY) {
            action = drone.runScanAction();
        }
        else if (action.type() == ActionType.HEADING) {
            if (changingDirection) {
                changingDirection = false;
                action = drone.runFlyAction();
            }
            else {
                changingDirection = true;
                if (position.getY() + 2 >= map.getHeight()) {
                    // If the drone is not facing the end of the map (vertically) do the following
                    action = drone.runHeadingAction(direction.right());
                }
                else {
                    // If the drone is not facing the end of the map (vertically) do the following
                    action = drone.runHeadingAction(direction.left());
                }
            }

        }
        else if (action.type() == ActionType.SCAN) {
            if (direction == Direction.SOUTH && position.getY() + 2 >= map.getHeight()) {
                // If the drone is not facing the end of the map (vertically) do the following
                action = drone.runHeadingAction(direction.right());
            }
            else if (direction == Direction.NORTH && position.getY() - 2 < 1) {
                // If the drone is not facing the end of the map (vertically) do the following
                action = drone.runHeadingAction(direction.left());
            }
            else {
                action = drone.runFlyAction();
            }
        }

        return action;    }
}
