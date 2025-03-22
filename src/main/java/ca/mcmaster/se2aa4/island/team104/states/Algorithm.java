package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap; 

public abstract class Algorithm {
    private Drone drone;
    private State state;
    public Algorithm(Drone drone, State state) {
        this.drone = drone;
        this.state = new StartState(drone);
    }

    public Action makeDecision() {
        return state.getNextAction();
    }

    public CoordinateMap getMap() {
        return drone.getMap();
    }

    public abstract void processResult(ActionResult result);
} 