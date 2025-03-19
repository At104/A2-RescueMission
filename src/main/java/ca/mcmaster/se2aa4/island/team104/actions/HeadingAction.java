package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;

public class HeadingAction implements Action {
    private final Direction direction;

    public HeadingAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject parameters = new JSONObject().put("direction", direction.toString());
        JSONObject action = new JSONObject().put("action", "heading").put("parameters", parameters);
        return action;
    }

    @Override
    public int getCost() {
        return 4;
    }
}