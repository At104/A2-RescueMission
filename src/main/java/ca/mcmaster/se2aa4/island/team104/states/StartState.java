package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.EchoAction;
import ca.mcmaster.se2aa4.island.team104.actions.HeadingAction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.results.EchoActionResult;

import java.util.HashMap;
import java.util.Map;

public class StartState extends State {
    private Map<Direction, Integer> dimensions;
    private boolean start;

    public StartState(Drone drone) {
        super(drone);
        dimensions = new HashMap<>();
        start = true;
    }

    @Override
    public Action getNextAction() {
        Drone drone = getDrone();
        Direction currentDirection = drone.getHeading();

        if (start) {
            start = false;
            return new HeadingAction(currentDirection.right());
        } else if (dimensions.containsKey(currentDirection)) {
            return new HeadingAction(currentDirection.left());
        } else {
            return new EchoAction(currentDirection);
        }
    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        Direction currentDirection = drone.getHeading();

        // Get echo range from EchoActionResult
        if (result.getEchoResult() != null) {
            EchoActionResult echoResult = result.getEchoResult();
            dimensions.put(currentDirection, echoResult.range());
            return this;
        }

        // Once we have all directions, we know the map size
        if (dimensions.size() == 4) {
            // Map size is the range in each direction
            int height = dimensions.get(Direction.NORTH) + dimensions.get(Direction.SOUTH) + 1;
            int width = dimensions.get(Direction.EAST) + dimensions.get(Direction.WEST) + 1;
            
            drone.getMap().setDimensions(height, width);
            return new SearchState(drone);
        }

        return this;
    }

    @Override
    public String toString() {
        return "StartingState";
    }
}
