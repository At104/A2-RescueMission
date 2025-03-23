package ca.mcmaster.se2aa4.island.team104.results;

import java.util.List;

//should only have one emergency site 
public record ScanActionResult(List<String> biomes,
                               List<String> creeks,
                               List<String> sites) {

    @Override
    public String toString() {
        return String.format("{ \"biomes\": %s, \"creeks\": %s, \"sites\": %s }",
                            biomes, creeks, sites);
    }
}

