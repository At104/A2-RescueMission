package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopAction implements Action {

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "stop");
        logger.info("** makeAction: {}", action.toString());
        return action;
    }

    @Override
    public int getCost() {
        return 5; //TODO Determine values
    }
}