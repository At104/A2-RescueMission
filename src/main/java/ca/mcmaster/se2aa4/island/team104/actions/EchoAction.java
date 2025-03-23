package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class EchoAction implements Action {
    private final Direction heading;

    public EchoAction(Direction heading) {
        this.heading = heading;
    }

    @Override
    public void execute(Drone drone, ActionResult result) {
        drone.decreaseBatteryOfAction(result);
    }

    @Override
    public JSONObject makeAction() {
        JSONObject command = new JSONObject();
        command.put("action", "echo");
        command.put("parameters", new JSONObject().put("direction", heading.toString()));
        return command;
    }

    @Override
    public ActionType type() {
        return ActionType.ECHO;
    }
}