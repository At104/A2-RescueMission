package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public interface Action {
    void execute(Drone drone, ActionResult result);
    JSONObject makeActionJsonObject();
    ActionType type();
}