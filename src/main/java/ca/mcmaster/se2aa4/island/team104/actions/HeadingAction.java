package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class HeadingAction implements Action {
    private final Direction heading;

    public HeadingAction(Direction heading) {
        this.heading = heading;
    }

    @Override
    public void execute(Drone drone, ActionResult result) {
        drone.decreaseBatteryOfAction(result);
        
        Direction currentDirection = drone.getHeading();
        Position currentPos = drone.getPosition();
        Position newPos;
        int x = currentPos.getX();
        int y = currentPos.getY();

        switch(currentDirection) {
            case NORTH:
                // Move forward 1, then left/right based on new direction
                y -= 1;
                x += (heading == Direction.WEST) ? -1 : 1;
                break;
            case SOUTH:
                // Move forward 1, then left/right based on new direction
                y += 1;
                x += (heading == Direction.WEST) ? -1 : 1;
                break;
            case EAST:
                // Move forward 1, then left/right based on new direction
                x += 1;
                y += (heading == Direction.NORTH) ? -1 : 1;
                break;
            case WEST:
                // Move forward 1, then left/right based on new direction
                x -= 1;
                y += (heading == Direction.NORTH) ? -1 : 1;
                break;
            default:
                break;
        }

        newPos = new Position(x, y);
        drone.updatePosition(newPos);
        drone.updateHeading(heading);
    }

    @Override
    public JSONObject makeActionJsonObject() {
        JSONObject command = new JSONObject();
        command.put("action", "heading");
        command.put("parameters", new JSONObject().put("direction", heading.toString()));
        return command;
    }

    @Override
    public ActionType type() {
        return ActionType.HEADING;
    }
}