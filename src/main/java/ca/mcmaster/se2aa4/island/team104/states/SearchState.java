package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class SearchState extends State {
    private Direction currentDirection;
    private boolean movingRight;
    
    public SearchState(Drone drone) {
        super(drone);
        this.currentDirection = Direction.EAST;  // Start moving right
        this.movingRight = true;
    }

    @Override
    public Action getNextAction() {
        return getDrone().runHeadingAction(currentDirection);
    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        
        // If we hit a boundary, change direction
        if (drone.getMap().isAtMapBoundary(drone.getPosition())) {
            if (movingRight) {
                // Move down and start moving left
                currentDirection = Direction.SOUTH;
                movingRight = false;
            } else {
                // Move down and start moving right
                currentDirection = Direction.SOUTH;
                movingRight = true;
            }
        } else {
            // Continue in current direction
            currentDirection = movingRight ? Direction.EAST : Direction.WEST;
        }
        
        // If we've reached the bottom of the map, we're done
        if (drone.getPosition().getY() >= drone.getMap().getHeight() - 1) {
            return new EndingState(drone);
        }
        
        // Continue moving
        return this;
    }

    @Override
    public String toString() {
        return "SearchState";
    }
} 