package ca.mcmaster.se2aa4.island.teamXXX;

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




}