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
            case North -> "NORTH";
            case South -> "SOUTH";
            case East -> "EAST";
            case West -> "WEST";
            default -> "NONE";
        };
    }

    public static Direction directionFromString(String direction) {
        return switch (direction) {
            case "NORTH" -> Direction.North;
            case "SOUTH" -> Direction.South;
            case "EAST" -> Direction.East;
            case "WEST" -> Direction.West;
            default -> Direction.NONE;
        };
    }

}
