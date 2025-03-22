package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
public class ScanAction implements Action {
    
    private static final int COST = 2;

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "scan");
        logger.info("** makeAction: {}", action.toString());
        return action;
    }

    @Override
    public int getCost() {
        return COST;
    }

    @Override
    public boolean execute(Drone drone) {
        if (drone.hasEnoughBattery(getCost())) {
            drone.consumeBattery(getCost());
            return true;
        }
        else {
            return false;
        }
    }

}