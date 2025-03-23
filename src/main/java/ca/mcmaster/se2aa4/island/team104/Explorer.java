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
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.states.BasicAlgorithm;


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

        try {
            drone = new Drone(new Position(1, 1), Direction.fromShortName(direction), batteryLevel, new CoordinateMap());
            logger.info("Drone initialized successfully.");
        } catch (Exception e) {
            logger.error("Error during Drone initialization: {}", e.getMessage(), e);
            throw e;
        }

        algorithm = new BasicAlgorithm(drone);

        logger.info("** Initialization complete");
    }

    @Override
    public String takeDecision() {
        JSONObject decision = algorithm.makeDecision().makeAction();
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

        try {
            algorithm.processResults(new ActionResult(response));
        } 
        catch (Exception e) {
            logger.error("Error in acknowledgeResults: {}", e);
            throw e;
        }
        
    }

    @Override
    public String deliverFinalReport() {
        String finalReport = algorithm.makeFinalReport();
        logger.info("** Final report: {}", finalReport);
        return finalReport;
    }
}