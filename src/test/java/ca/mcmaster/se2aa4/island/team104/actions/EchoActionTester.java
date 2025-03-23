package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoActionTester {
    private DroneTestFactory droneTestFactory = new DroneTestFactory();

    @Test
    public void testEchoAction() {
        EchoAction echoAction = new EchoAction(Direction.EAST);
        JSONObject obj = echoAction.makeAction();
        assertEquals(ActionType.ECHO, echoAction.type());
        assertEquals("echo", obj.getString("action"));
        assertEquals("EAST", obj.getJSONObject("parameters").getString("direction"));
    }

    @Test
    public void testEchoActionRunning() {
        Drone drone = droneTestFactory.initDrone();
        EchoAction echoAction = new EchoAction(Direction.EAST);
        ActionResult result = new ActionResult(echoAction.makeAction());
        echoAction.execute(drone, result);
        assertEquals(99, drone.getBatteryLevel());
        assertEquals("ok", result.getJSON().getString("status"));

    }
}
