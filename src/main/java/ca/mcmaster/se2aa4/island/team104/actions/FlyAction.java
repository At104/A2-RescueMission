package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;

public class FlyAction implements Action {
    private static final int COST = 2;

    @Override
    public JSONObject makeAction() {
        return new JSONObject().put("action", "fly");
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

        Position currentPos = drone.getPosition();
        Position nextPos;
        int x = currentPos.getX();
        int y = currentPos.getY();

        // Calculate next position based on current heading
        switch(drone.getHeading()) {
            case NORTH:
                y += 1;
                break;
            case SOUTH:
                y -= 1;
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

        nextPos = new Position(x, y);
        
        if (!drone.getMap().isValidPosition(nextPos)) {
            return false;
        }

        drone.consumeBattery(COST);
        drone.updatePosition(nextPos);
        return true;
    }
}
