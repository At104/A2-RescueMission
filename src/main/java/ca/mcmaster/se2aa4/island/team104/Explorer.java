package ca.mcmaster.se2aa4.island.team104;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Position;
import ca.mcmaster.se2aa4.island.team104.states.BasicAlgorithm;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.actions.Action;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private Drone drone;
    private BasicAlgorithm algorithm;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}", info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        // Create initial objects
        CoordinateMap map = new CoordinateMap(0, 0); // Size will be determined by echo
        Position startPos = new Position(1, 1); // Start at (1,1)
        Direction startDir = Direction.valueOf(direction);
        
        // Initialize drone and algorithm
        drone = new Drone(startPos, startDir, batteryLevel, map);
        algorithm = new BasicAlgorithm(drone);

        logger.info("** Initialization complete");
    }

    @Override
    public String takeDecision() {
        Action action = algorithm.makeDecision();
        JSONObject decision = action.makeAction();
        logger.info("** Decision: {}", decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n" + response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        logger.info("Drone battery level is now {}", drone.getBatteryLevel());

       
            ActionResult result = new ActionResult(response);
            Action action = algorithm.makeDecision();
            action.execute(drone, result);
            algorithm.processResult(result);
        
    }

    @Override
    public String deliverFinalReport() {
        String finalReport = algorithm.makeFinalReport();
        logger.info("** Final report: {}", finalReport);
        return finalReport;
    }
}