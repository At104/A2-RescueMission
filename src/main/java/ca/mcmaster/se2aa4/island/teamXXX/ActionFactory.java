package ca.mcmaster.se2aa4.island.teamXXX;

public class ActionFactory {

    public Action createAction(boolean isLandFound, boolean isOutOfRange) {
        if (isLandFound) {
            return new StopAction();
        }
        else if (isOutOfRange) {
            return new ReturnAction();
        }
        else {
            return new RadarAction();
        }
    }
}