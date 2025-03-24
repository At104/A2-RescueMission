package ca.mcmaster.se2aa4.island.team104.algorithm.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public abstract class State {
    private Drone drone;

    public State(Drone drone) {
        this.drone = drone;
    }

    public Drone getDrone() {
        return drone;
    }

    public abstract State getNextState(ActionResult result);
    
    public abstract Action getNextAction();
}
