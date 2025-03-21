package ca.mcmaster.se2aa4.island.team104.drone;

public class PointOfInterest {
    private final POIType type;
    private final String id;
    private final Position position;

    public enum POIType {
        CREEK,
        EMERGENCY_SITE
    }

    public PointOfInterest(POIType type, String id, Position position) {
        this.type = type;
        this.id = id;
        this.position = position;
    }

    public POIType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isCreek() {
        return type == POIType.CREEK;
    }

    public boolean isEmergencySite() {
        return type == POIType.EMERGENCY_SITE;
    }

    @Override
    public String toString() {
        return type + " (ID: " + id + ") at " + position;
    }
} 