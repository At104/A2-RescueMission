package ca.mcmaster.se2aa4.island.team104.drone;

/**
 * Represents a Point of Interest (POI) on the island. A POI is a location with
 * a specific type.
 */
public record PointOfInterest(
    /**
     * ID of the POI
     */
    String id,
    /**
     * Position detected of the POI
     */
    Position position,
    /**
     * Type of the POI
     */
    POIType type) {

    @Override
    public String toString() {
        return String.format("{id=%s, position=%s, type=%s}", id, position, type);
    }
}

/**
 * Represents the type of a point of interest.
 */
enum POIType {
    /**
     * A creek.
     */
    CREEK,
    /**
     * An emergency site
     */
    EMERGENCY_SITE
} 