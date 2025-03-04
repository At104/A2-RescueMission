package ca.mcmaster.se2aa4.island.teamXXX;

public class DecisionHandler {

    private final ActionFactory actionFactory = new ActionFactory();

    public String takeDecision(boolean isLandFound, boolean isOutOfRange) {
        Action action = actionFactory.createAction(isLandFound, isOutOfRange);
        return action.makeDecision().toString();
    }
}