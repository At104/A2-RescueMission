package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class StartingState extends State {

    public StartingState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(ActionResult result) {
        return new ScanningState(getDrone());
    }

    @Override
    public String toString() {
        return "StartingState";
    }


}
