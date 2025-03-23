package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class EndingState extends State {

    public EndingState(Drone drone) {
        super(drone);
    }

    @Override
    public Action getNextAction() {
        return getDrone().runStopAction();
    }

    @Override
    public State getNextState(ActionResult result) {
        return this;
    }

   
}
