package ca.mcmaster.se2aa4.island.team104.actions;

import org.junit.Test;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopActionTester {

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
        ActionResult result = new ActionResult(stopAction.makeAction());
        stopAction.execute(drone, result);
        assertEquals(97, drone.getBatteryLevel());
        assertEquals("ok", result.getJSON().getString("status"));
    }
}
