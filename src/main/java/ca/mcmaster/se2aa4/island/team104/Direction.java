package ca.mcmaster.se2aa4.island.team104;

public enum Direction {

    North, South, East, West, NONE;

    public Direction left() {
        return switch (this) {
            case North -> Direction.West;
            case South -> Direction.East;
            case East -> Direction.North;
            case West -> Direction.South;
            default -> Direction.NONE;
        };
    }

    public Direction right() {
        return switch (this) {
            case North -> Direction.East;
            case South -> Direction.West;
            case East -> Direction.South;
            case West -> Direction.North;
            default -> Direction.NONE;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case North -> "N";
            case South -> "S";
            case East -> "E";
            case West -> "W";
            default -> "NONE";
        };
    }

}
