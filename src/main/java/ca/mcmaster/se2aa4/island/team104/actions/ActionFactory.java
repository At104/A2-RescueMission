package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;


public class ActionFactory {

    public Action createScanAction() {
        return new ScanAction();
    }

    public Action createFlyAction() {
        return new FlyAction();
    }

    public Action createStopAction() {
        return new StopAction();
    }

    public Action createHeadingAction(Direction direction) {
        return new HeadingAction(direction);
    }

    public Action createEchoAction(Direction heading) {
        return new EchoAction(heading);
    }




}