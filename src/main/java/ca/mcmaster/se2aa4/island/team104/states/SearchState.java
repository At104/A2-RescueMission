package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.ActionType;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchState extends State {
    private static final Logger log = LogManager.getLogger(SearchState.class);
    private Action action;
    private boolean changingDirection;
    private boolean leftLandFound = false;
    private boolean vertLandFound = false;

    public SearchState(Drone drone) {
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

        if (drone.getBatteryLevel() < 35) {
            return new EndingState(drone);
        }
        if(!vertLandFound && leftLandFound && action.type() == ActionType.HEADING) {
            log.warn("Heading not found in action result.");
            return new BigTurnState(drone);
            //return new EndingState(drone);
        }
        
        if (action.type() == ActionType.FLY) {
            if (direction == Direction.NORTH) {
                if (position.getY() - 4 < 1 && position.getX() + 4 > map.getWidth()) {
                    return new BigTurnState(drone);
                    //return new EndingState(drone);
                }
            }
            if (direction == Direction.SOUTH) {
                if (position.getY() + 4 >= map.getHeight() && position.getX() + 4 > map.getWidth()) {
                    return new BigTurnState(drone);
                    //return  new EndingState(drone);
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
                log.info("vert land resetting to false");
                vertLandFound = false;
            } 
            else {
                changingDirection = true;
                if (position.getY() + 2 >= map.getHeight()) {
                    // If the drone is not facing the end of the map (vertically) do the following
                    action = drone.runHeadingAction(direction.left());


                } 
                else {
                    // If the drone is not facing the end of the map (vertically) do the following
                    action = drone.runHeadingAction(direction.right());


                }

            }

        } 
        else if (action.type() == ActionType.SCAN) {
            if (direction == Direction.SOUTH && position.getY() + 2 >= map.getHeight()) {
                // If the drone is not facing the end of the map (vertically) do the following
                action = drone.runHeadingAction(direction.left());
            } 
            else if (direction == Direction.NORTH && position.getY() - 2 < 1) {
                // If the drone is not facing the end of the map (vertically) do the following
                action = drone.runHeadingAction(direction.right());
            }
            else {
                action = drone.runFlyAction();
                if(!leftLandFound){
                    leftLandFound = !drone.getCurrentBiome().contains("OCEAN");
                }
                if(!vertLandFound){
                    vertLandFound = !drone.getCurrentBiome().contains("OCEAN");
                }
            }
        }
        
        return action;
    }
} 