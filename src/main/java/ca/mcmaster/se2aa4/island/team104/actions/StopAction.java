package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class StopAction implements Action {
    @Override
    public void execute(Drone drone, ActionResult result) {
        drone.decreaseBatteryOfAction(result);
    }

    @Override
    public JSONObject makeAction() {
        JSONObject command = new JSONObject();
        command.put("action", "stop");
        return command;
    }

    @Override
    public ActionType type() {
        return ActionType.STOP;
    }
}