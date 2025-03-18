package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.Direction;
import org.json.JSONObject;

public class EchoAction implements Action {
    private final Direction direction;

    public EchoAction(Direction direction) {
        this.direction = direction;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "radar").put("direction", direction.toString());
        return action;
    }

    @Override
    public int getCost() {
        return 5; //TODO Determine values
    }
}