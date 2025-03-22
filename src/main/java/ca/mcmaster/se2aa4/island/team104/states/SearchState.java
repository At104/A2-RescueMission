package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.actions.HeadingAction;
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
        return new HeadingAction(currentDirection);
    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        
        if (drone.getMap().isAtMapBoundary(drone.getPosition())) {
            if (movingRight) {
                
                currentDirection = Direction.SOUTH;
                movingRight = false;
            } else {
           
                currentDirection = Direction.SOUTH;
                movingRight = true;
            }
        } else {
            
            currentDirection = movingRight ? Direction.EAST : Direction.WEST;
        }
        
     
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