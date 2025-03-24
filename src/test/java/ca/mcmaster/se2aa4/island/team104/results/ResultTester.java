package ca.mcmaster.se2aa4.island.team104.results;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultTester {
    private ResultTesterFactory factory;
    private ActionResult echoResult;
    private ActionResult scanResult;

    @BeforeEach
    public void setUp() {
        factory = new ResultTesterFactory();
        echoResult = factory.initEchoActionResult(10, "OK", 5, "GROUND");
        scanResult = factory.initScanActionResult(10, "OK");
    }

    @Test
    public void testEchoActionResult() {
        assertEquals(10, this.echoResult.getCost());
        assertEquals(5, echoResult.getEchoResult().range());
        assertTrue(echoResult.getEchoResult().groundFound());
    }

    @Test
    public void testScanActionResult() {
        assertEquals(10, scanResult.getCost());
        assertEquals(0, scanResult.getScanResult().biomes().size());
        assertEquals(0, scanResult.getScanResult().creeks().size());
        assertEquals(0, scanResult.getScanResult().sites().size());
    }

    @Test
    public  void testEchoActionResultSpecific() {
        EchoActionResult echoActionResult = new EchoActionResult(5, true);
        assertEquals("{ \"range\": 5, \"found\": \"GROUND\" }", echoActionResult.toString());
        assertEquals(5, echoActionResult.range());
        assertTrue(echoActionResult.groundFound());
    }

    @Test
    public void testEchoActionResultOutOfRange() {
        EchoActionResult echoActionResult = new EchoActionResult(0, false);
        assertEquals("{ \"range\": 0, \"found\": \"OUT_OF_RANGE\" }", echoActionResult.toString());
        assertEquals(0, echoActionResult.range());
        assertFalse(echoActionResult.groundFound());
    }

    @Test
    public void testScanActionResultSpecific() {
        List<String> creeks = List.of("creek1", "creek2");
        List<String> biomes = List.of("biome1", "biome2");
        List<String> sites = List.of("site1", "site2");
        ScanActionResult scanActionResult = new ScanActionResult(biomes, creeks, sites);
        assertEquals("{ \"biomes\": [biome1, biome2], \"creeks\": [creek1, creek2], \"sites\": [site1, site2] }", scanActionResult.toString());
        assertEquals(biomes, scanActionResult.biomes());
        assertEquals(creeks, scanActionResult.creeks());
        assertEquals(sites, scanActionResult.sites());

    }
}