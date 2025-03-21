package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import ca.mcmaster.se2aa4.island.team104.results.ScanActionResult;

public class StartingState extends State {

    public StartingState(Drone drone) {
        super(drone);
    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        Action nextAction = drone.runScanAction();
        drone.decreaseBatteryOfAction(result);
        //return new ScanningState(drone);
        //TODO add actual next state based on algo implemented
        return null;
    }

    @Override
    public String toString() {
        return "StartingState";
    }


}
