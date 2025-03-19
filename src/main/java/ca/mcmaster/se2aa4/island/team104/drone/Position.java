package ca.mcmaster.se2aa4.island.team104.drone;

public class Position {
    private final int x;
    private final int y;
    private Biome biome;
    private PointOfInterest poi;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.biome = Biome.UNKNOWN;
        this.poi = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public PointOfInterest getPointOfInterest() {
        return poi;
    }

    public void setPointOfInterest(PointOfInterest poi) {
        this.poi = poi;
    }

    public boolean hasPointOfInterest() {
        return poi != null;
    }

    public boolean isLand() {
        return biome != Biome.OCEAN && biome != Biome.UNKNOWN;
    }

    public boolean isWater() {
        return biome == Biome.OCEAN;
    }

    public boolean isCreek() {
        return poi == PointOfInterest.CREEK;
    }

    public boolean isEmergencySite() {
        return poi == PointOfInterest.EMERGENCY_SITE;
    }

    public enum Biome {
        OCEAN,
        BEACH,
        FOREST,
        GRASSLAND,
        UNKNOWN
    }

    public enum PointOfInterest {
        CREEK,
        EMERGENCY_SITE
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public String toStringWithPOI() {
        String id = hashCode() + "";
        return "POI: " + poi + " ID: " + id + " at (" + x + ", " + y + ")";
    }
} 