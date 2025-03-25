package ca.mcmaster.se2aa4.island.team104.drone;


public record PointOfInterest(
    String id,
    Position position,
    POIType type) {

    @Override
    public String toString() {
        return String.format("{id=%s, position=%s, type=%s}", id, position, type);
    }
}

enum POIType {
    CREEK,
    EMERGENCY_SITE
} 