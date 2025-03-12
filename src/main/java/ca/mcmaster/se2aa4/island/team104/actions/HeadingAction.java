package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.Direction;
import org.json.JSONObject;

public class HeadingAction implements Action {
    private final Direction direction;

    public HeadingAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "heading").put("direction", direction);
        return action;
    }
}