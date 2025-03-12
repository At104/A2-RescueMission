package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScanAction implements Action {

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject makeAction() {
        JSONObject action = new JSONObject().put("action", "scan");
        logger.info("** makeAction: {}", action.toString());
        return action;
    }
}