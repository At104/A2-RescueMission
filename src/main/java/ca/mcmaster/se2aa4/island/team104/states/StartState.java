package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.ActionType;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.drone.Position;

import java.util.HashMap;
import java.util.Map;

public class StartState extends State {

    private Map<Direction, Integer> dimensions;
    private Action action;
    private boolean start;

    public StartState(Drone drone) {
        super(drone);
        dimensions = new HashMap<>();
        drone.updatePosition(new Position(1, 1));
        start = true;
    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        action.execute(drone, result);

        Direction currentDirection = drone.getHeading();

        // If you are checking out the range of the direction
        if (action.type() == ActionType.ECHO) {

            
            dimensions.put(currentDirection, result.getEchoResult().range());
            return this;
        } else if (action.type() == ActionType.HEADING) {
            
            drone.updateHeading(currentDirection);

            if (dimensions.size() == 4) {
               
                int height = (dimensions.get(Direction.NORTH) + dimensions.get(Direction.SOUTH) + 1);
                int width = (dimensions.get(Direction.EAST) + dimensions.get(Direction.WEST) + 1);

                
                drone.getMap().setDimensions(height, width);

               
                return new SearchState(drone);
            }

            return this;
        }

        return this;

    }

    @Override
    public Action getNextAction() {
        Drone drone = getDrone();
        Direction currentDirection = drone.getHeading();

        // Turn right if it is the first action
        if (start) {
            start = false;

            action = drone.runHeadingAction(currentDirection.right());
        } else if (dimensions.containsKey(currentDirection)) {
            // If the direction has been checked, move to the next direction
            action = drone.runHeadingAction(currentDirection.left());

        } else {
            action = drone.runEchoAction(currentDirection);
        }

        return action;
    }

}

