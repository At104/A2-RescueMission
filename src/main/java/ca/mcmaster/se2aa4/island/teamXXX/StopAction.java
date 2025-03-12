package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StopAction implements Action {

    private final Logger logger = LogManager.getLogger();

    @Override
    public JSONObject makeDecision() {
        JSONObject decision = new JSONObject();
        decision.put("action", "stop");
        logger.info("** Decision: {}", decision.toString());
        return decision;
    }

    @Override
    public int getCost() {
        return 5; // Stop action costs 5 battery
    }
}