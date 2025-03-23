package ca.mcmaster.se2aa4.island.team104.actions;

import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlyActionTester {
    private Drone drone;

    @BeforeEach
    public void setup() {
        drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
    }

    @Test
    public void testFlyAction() {
        FlyAction flyAction = new FlyAction();
        JSONObject obj = flyAction.makeAction();
        assertEquals(ActionType.FLY, flyAction.type());
        assertEquals("fly", obj.getString("action"));
    }

    @Test
    public void testFlyActionRunning() {
        FlyAction flyAction = new FlyAction();
        ActionResult result = new ActionResult(flyAction.makeAction());
        flyAction.execute(drone, result);
        assertEquals(98, drone.getBatteryLevel());
        assertEquals("ok", result.getJSON().getString("status"));
        assertEquals(3, drone.getPosition().getX());
        assertEquals(0, drone.getPosition().getY());
    }


}
