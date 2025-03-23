package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingActionTester {
    private Drone drone;

    @BeforeEach
    public void setup() {
        drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
    }

    @Test
    public void testHeadingAction() {
        HeadingAction headingAction = new HeadingAction(Direction.SOUTH);
        JSONObject obj = headingAction.makeAction();
        assertEquals(ActionType.HEADING, headingAction.type());
        assertEquals("heading", obj.getString("action"));
        assertEquals("SOUTH", obj.getJSONObject("parameters").getString("direction"));
    }

    @Test
    public void testHeadingActionRunning() {
        HeadingAction headingAction = new HeadingAction(Direction.SOUTH);
        ActionResult result = new ActionResult(headingAction.makeAction());
        headingAction.execute(drone, result);
        assertEquals(96, drone.getBatteryLevel());
        assertEquals("ok", result.getJSON().getString("status"));
        assertEquals(Direction.EAST, drone.getHeading());
        assertEquals(3, drone.getPosition().getX());
        assertEquals(-3, drone.getPosition().getY());

    }
}
