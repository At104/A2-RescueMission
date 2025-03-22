package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;

public interface Action {
    JSONObject makeAction();
    int getCost();
    boolean execute(Drone drone);
    

}