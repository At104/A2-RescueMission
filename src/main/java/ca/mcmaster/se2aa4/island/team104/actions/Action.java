package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public interface Action {
    // Creates the JSON request for the action
    JSONObject makeAction();
    
    // Returns the battery cost of the action
    int getCost();
    
    // Executes the action on the drone, returns true if successful
    boolean execute(Drone drone);
    
    // Executes the action and processes the result (for Scan/Echo actions)
    default boolean execute(Drone drone, ActionResult result) {
        return execute(drone);  // Default just calls the simple version
    }
}