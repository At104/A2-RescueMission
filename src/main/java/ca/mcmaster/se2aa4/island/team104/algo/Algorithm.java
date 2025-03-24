package ca.mcmaster.se2aa4.island.team104.algo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;
import ca.mcmaster.se2aa4.island.team104.drone.PointOfInterest;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.states.State;

public abstract class Algorithm {
    private Drone drone;
    private State state;
    private final Logger logger = LogManager.getLogger();
    public Algorithm(Drone drone) {
        this.drone = drone;
        this.state = getStartState(drone);
    }

    public Drone getDrone() {
        return drone;
    }

    public Action makeDecision() {
        return state.getNextAction();
    }

    public void processResults(ActionResult result) {
        state = state.getNextState(result);
    }

    protected abstract State getStartState(Drone drone);

    public String makeFinalReport() {
        CoordinateMap mapInfo = drone.getMap();
        
        if (mapInfo.getCreeks().isEmpty()) {
            return "no creek found";
        } 
        else {
            PointOfInterest site = mapInfo.getSite();
            logger.info("Site: {}", site);

            if (site != null) {
                PointOfInterest creek = mapInfo.getClosestCreek();

                JSONObject obj = new JSONObject();

                obj.put("emergency_site_position", site.position().toString());
                obj.put("emergency_site_id", site.id());
                obj.put("nearest_creek_position", creek.position().toString());
                obj.put("nearest_creek_id", creek.id());

                return obj.toString();
            } 
            else {
                //Should never happen
                JSONObject obj = new JSONObject();
                logger.info("Creeks: {}", mapInfo.getCreeks());
                obj.put("creek", mapInfo.getCreeks().get(0).id());
                return obj.toString();
            }
        }
    }
} 