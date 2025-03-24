package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeadingActionTester {
    private static final Logger log = LogManager.getLogger(HeadingActionTester.class);
    private Drone drone;
    private JSONObject json;

    @BeforeEach
    public void setup() {
        drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
        json = new JSONObject();
        json.put("cost", 4);
        json.put("status", "OK");
        json.put("extras", new JSONObject());
    }

    @Test
    public void testHeadingAction() {
        HeadingAction headingAction = new HeadingAction(Direction.SOUTH);
        JSONObject obj = headingAction.makeAction();
        assertEquals(ActionType.HEADING, headingAction.type());
        assertEquals("heading", obj.getString("action"));
        assertEquals("S", obj.getJSONObject("parameters").getString("direction"));
    }

    @Test
    public void testHeadingActionRunning() {
        HeadingAction headingAction = new HeadingAction(Direction.SOUTH);
        ActionResult result = new ActionResult(json);
        headingAction.execute(drone, result);
        assertEquals(96, drone.getBatteryLevel());
        assertEquals("OK", result.getJSON().getString("status"));
        assertEquals(Direction.SOUTH, drone.getHeading());
        assertEquals(1, drone.getPosition().getX());
        assertEquals(1, drone.getPosition().getY());

    }
}
