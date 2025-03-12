package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.Direction;

public class ActionFactory {

    public String createScanAction() {
        return new ScanAction().toString();
    }

    public String createFlyAction() {
        return new FlyAction().toString();
    }

    public String createStopAction() {
        return new StopAction().toString();
    }

    public String createHeadingAction(Direction direction) {
        return new HeadingAction(direction).toString();
    }




}