package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EchoActionTester {
    private DroneTestFactory droneTestFactory = new DroneTestFactory();
    private JSONObject json;

    @BeforeEach
    public void setup() {
        json = new JSONObject();
        json.put("cost", 1);
        json.put("status", "OK");
        json.put("extras", new JSONObject());
    }

    @Test
    public void testEchoAction() {
        EchoAction echoAction = new EchoAction(Direction.EAST);
        JSONObject obj = echoAction.makeAction();
        assertEquals(ActionType.ECHO, echoAction.type());
        assertEquals("echo", obj.getString("action"));
        assertEquals("E", obj.getJSONObject("parameters").getString("direction"));
    }

    @Test
    public void testEchoActionRunning() {
        Drone drone = droneTestFactory.initDrone();
        EchoAction echoAction = new EchoAction(Direction.EAST);
        ActionResult result = new ActionResult(json);
        echoAction.execute(drone, result);
        assertEquals(99, drone.getBatteryLevel());
        assertEquals("OK", result.getJSON().getString("status"));

    }
}
