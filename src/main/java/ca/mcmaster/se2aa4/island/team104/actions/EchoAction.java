package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.results.EchoActionResult;

public class EchoAction implements Action {
    private static final int COST = 1;
    private final Direction direction;

    public EchoAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject parameters = new JSONObject().put("direction", direction.toString());
        return new JSONObject()
            .put("action", "echo")
            .put("parameters", parameters);
    }

    @Override
    public int getCost() {
        return COST;
    }

    @Override
    public boolean execute(Drone drone) {
        return execute(drone, null);
    }

    @Override
    public boolean execute(Drone drone, ActionResult result) {
        if (drone.hasEnoughBattery(COST)) {
            drone.consumeBattery(COST);
            return true;
        }
        else {
            return false;
        }
    }
}