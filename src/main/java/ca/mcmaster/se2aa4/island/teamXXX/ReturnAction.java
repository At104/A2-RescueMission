package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReturnAction implements Action {

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject makeDecision() {
        JSONObject decision = new JSONObject();
        decision.put("action", "return");
        logger.info("** Decision: {}", decision.toString());
        return decision;
    }
}