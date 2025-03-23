package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Position;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class FlyAction implements Action {
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
                y -= 1;
                break;
            case SOUTH:
                y += 1;
                break;
            case EAST:
                x += 1;
                break;
            case WEST:
                x -= 1;
                break;
            default:
                break;
        }

        newPos = new Position(x, y);
        drone.updatePosition(newPos);
    }

    @Override
    public JSONObject makeAction() {
        JSONObject command = new JSONObject();
        command.put("action", "fly");
        return command;
    }

    @Override
    public ActionType type() {
        return ActionType.FLY;
    }
}
