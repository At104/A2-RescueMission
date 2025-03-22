package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;

public class HeadingAction implements Action {
    private static final int COST = 4;
    private final Direction newDirection;

    public HeadingAction(Direction direction) {
        this.newDirection = direction;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject parameters = new JSONObject().put("direction", newDirection.toString());
        return new JSONObject()
            .put("action", "heading")
            .put("parameters", parameters);
    }

    @Override
    public int getCost() {
        return COST;
    }

    @Override
    public boolean execute(Drone drone) {
        if (drone.hasEnoughBattery(COST)) {
            drone.consumeBattery(COST);
        }
        else {
            return false;
        }
        

        Direction currentDirection = drone.getHeading();
        Position currentPos = drone.getPosition();
        Position newPos;
        int x = currentPos.getX();
        int y = currentPos.getY();

        switch(currentDirection) {
            case NORTH:
                // Move forward 1, then left/right based on new direction
                y += 1;
                x += (newDirection == Direction.WEST) ? -1 : 1;
                break;
            case SOUTH:
                // Move forward 1, then left/right based on new direction
                y -= 1;
                x += (newDirection == Direction.WEST) ? -1 : 1;
                break;
            case EAST:
                // Move forward 1, then left/right based on new direction
                x += 1;
                y += (newDirection == Direction.NORTH) ? 1 : -1;
                break;
            case WEST:
                // Move forward 1, then left/right based on new direction
                x -= 1;
                y += (newDirection == Direction.NORTH) ? 1 : -1;
                break;
            default:
                break;
        }

        newPos = new Position(x, y);
        
        // Check if the final position is valid
        if (!drone.getMap().isValidPosition(newPos)) {
            return false;
        }

        // Execute the turn
        drone.updatePosition(newPos);
        drone.updateHeading(newDirection);
        return true;
    }
}