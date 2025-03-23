package ca.mcmaster.se2aa4.island.team104.states;

import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;

public abstract class Algorithm {
    private Drone drone;
    private State state;
    public Algorithm(Drone drone, State state) {
        this.drone = drone;
        this.state = state;
    }

    public Action makeDecision() {
        return state.getNextAction();
    }

    public CoordinateMap getMap() {
        return drone.getMap();
    }

    protected State getState() {
        return state;
    }

    protected void setState(State state) {
        this.state = state;
    }

    public State processResult(ActionResult result) {
        // Process scan results first
        drone.processScanResult(result);
        // Then update state
        return state.getNextState(result);
    }

    public String makeFinalReport() {
        CoordinateMap map = drone.getMap();
        if (map.getSite() == null) {
            return "Site not found";
        }
        else {
            if (map.getCreeks().size() > 0) {
                JSONObject json = new JSONObject(); 
                json.put("site", map.getSite().toString());
                json.put("creeks", map.getCreeks().toString());
                json.put("nearest creek", map.getClosestCreek().toString());
                return json.toString();
            }
            else {
                JSONObject json = new JSONObject();
                json.put("site", map.getSite().toString());
                return json.toString();
            }
        }
    }
} 