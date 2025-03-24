package ca.mcmaster.se2aa4.island.team104.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopActionTester {
    private JSONObject json;

    @BeforeEach
    public void setup() {
        json = new JSONObject();

        json.put("cost", 3);
        json.put("status", "OK");
        json.put("extras", new JSONObject());
    }

    @Test
    public void testStopAction() {
        StopAction stopAction = new StopAction();
        JSONObject obj = stopAction.makeAction();
        assertEquals(ActionType.STOP, stopAction.type());
        assertEquals("stop", obj.getString("action"));
    }

    @Test
    public void testStopActionRunning() {
        Drone drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
        StopAction stopAction = new StopAction();
        ActionResult result = new ActionResult(json);
        stopAction.execute(drone, result);
        assertEquals(97, drone.getBatteryLevel());
        assertEquals("OK", result.getJSON().getString("status"));
    }
}
