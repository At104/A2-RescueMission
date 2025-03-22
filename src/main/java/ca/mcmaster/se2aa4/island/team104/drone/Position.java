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

    public Position getNextPosition(Direction direction) {
        int newX = x;
        int newY = y;
        
        switch (direction) {
            case NORTH -> newY++;
            case SOUTH -> newY--;
            case EAST -> newX++;
            case WEST -> newX--;
            default -> throw new IllegalArgumentException("Unexpected value: " + direction);
        }
        
        return new Position(newX, newY);
    }

    public int getManhattanDistance(Position other) {
        return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
    }

    public boolean isAdjacent(Position other) {
        return this.getManhattanDistance(other) == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
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

    public enum Biome {
        OCEAN,
        BEACH,
        FOREST,
        GRASSLAND,
        UNKNOWN
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
} 