package ca.mcmaster.se2aa4.island.team104.actions;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.DroneTestFactory;
import ca.mcmaster.se2aa4.island.team104.results.ActionResult;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ScanActionTester {
    private Drone drone;

    @BeforeEach
    public void setup() {
        drone = new DroneTestFactory().initDrone(0, 0, 100, "EAST");
    }

    @Test
    public void testScanAction() {
        ScanAction scanAction = new ScanAction();
        JSONObject obj = scanAction.makeAction();
        assertEquals(ActionType.SCAN, scanAction.type());
        assertEquals("scan", obj.getString("action"));
    }

    @Test
    public void testScanActionRunning() {
        ScanAction scanAction = new ScanAction();
        ActionResult result = new ActionResult(scanAction.makeAction());
        scanAction.execute(drone, result);
        assertEquals(98, drone.getBatteryLevel());
        assertEquals("ok", result.getJSON().getString("status"));
        assertEquals(0, drone.getPosition().getX());
        assertEquals(0, drone.getPosition().getY());
        assertFalse(drone.getMap().hasCreeks());
        assertFalse(drone.getMap().hasCreeks());
    }
}
