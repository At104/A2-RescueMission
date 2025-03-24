package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.actions.Action;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;

public class BigTurnState extends State {
    private int uTurns = 0;
    private boolean facingNorth;
    Action action;

    public BigTurnState(Drone drone){
        super(drone);
        facingNorth = drone.getHeading() == Direction.NORTH;

    }

    @Override
    public State getNextState(ActionResult result) {
        Drone drone = getDrone();
        action.execute(drone, result);

        if(uTurns == 3){
            return new SearchStateSecond(drone);
        }
        return this;
    }

    @Override
    public Action getNextAction() {
        if (uTurns == 0){
            uTurns++;
            action = getDrone().runHeadingAction(Direction.WEST);

        } else if (uTurns == 1) {
            uTurns++;
            action = getDrone().runFlyAction();
        } else if (uTurns == 2 ) {
            uTurns++;
            if(facingNorth){
                action = getDrone().runHeadingAction(Direction.SOUTH);
            } else {
                action = getDrone().runHeadingAction(Direction.NORTH);
            }
        }
        return action;
    }

}

