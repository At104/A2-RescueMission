package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;

public class EchoAction implements Action {
    private final Direction heading;

    public EchoAction(Direction heading) {
        this.heading = heading;
    }

    @Override
    public JSONObject makeAction() {
        JSONObject parameters = new JSONObject().put("direction", heading.toString());
        JSONObject action = new JSONObject().put("action", "echo").put("parameters", parameters);
        return action;
    }

    @Override
    public int getCost() {
        return 1;
    }
}