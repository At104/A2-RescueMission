package ca.mcmaster.se2aa4.island.team104.actions;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScanActionTester {
    private Drone drone;
    private JSONObject json;

    @BeforeEach
    public void setup() {
        drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
        json = new JSONObject();
        json.put("cost", 2);
        json.put("status", "OK");
        json.put("extras", new JSONObject());
    }

    @Test
    public void testScanAction() {
        ScanAction scanAction = new ScanAction();
        JSONObject obj = scanAction.makeActionJsonObject();
        assertEquals(ActionType.SCAN, scanAction.type());
        assertEquals("scan", obj.getString("action"));
    }

    @Test
    public void testScanActionRunning() {
        ScanAction scanAction = new ScanAction();
        ActionResult result = new ActionResult(json);
        scanAction.execute(drone, result);
        assertEquals(98, drone.getBatteryLevel());
        assertEquals("OK", result.getJSON().getString("status"));
        assertEquals(0, drone.getPosition().getX());
        assertEquals(0, drone.getPosition().getY());
        assertFalse(drone.getMap().hasCreeks());
        assertFalse(drone.getMap().hasCreeks());
    }
}
