package ca.mcmaster.se2aa4.island.team104.results;


public record EchoActionResult(int range, boolean groundFound) {

    @Override
    public String toString() {
        return String.format("{ \"range\": %d, \"found\": \"%s\" }", range,
                                groundFound ? "GROUND" : "OUT_OF_RANGE");
    }

}
