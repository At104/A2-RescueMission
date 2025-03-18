package ca.mcmaster.se2aa4.island.team104.actions;


import org.json.JSONObject;

public class FlyAction implements Action {

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "fly");
        return action;
    }

    @Override
    public int getCost() {
        return 2;
    }

}
