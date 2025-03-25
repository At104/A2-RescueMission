package ca.mcmaster.se2aa4.island.team104.drone;

public enum Direction {
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    NONE("NONE");

    private final String shortName;
    private Direction left;
    private Direction right;

    Direction(String shortName) {
        this.shortName = shortName;
    }

    static {
        NORTH.left = WEST;
        NORTH.right = EAST;
        SOUTH.left = EAST;
        SOUTH.right = WEST;
        EAST.left = NORTH;
        EAST.right = SOUTH;
        WEST.left = SOUTH;
        WEST.right = NORTH;
        NONE.left = NONE;
        NONE.right = NONE;
    }

    public Direction left() {
        return left;
    }

    public Direction right() {
        return right;
    }

    @Override
    public String toString() {
        return shortName;
    }

    public static Direction fromShortName(String shortName) {
        switch (shortName.toUpperCase()) {
            case "N":
                return NORTH;
            case "E":
                return EAST;
            case "S":
                return SOUTH;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Invalid direction: " + shortName);
        }
    }

    public static Direction directionFromString(String direction) {
        return switch (direction) {
            case "NORTH" -> Direction.NORTH;
            case "SOUTH" -> Direction.SOUTH;
            case "EAST" -> Direction.EAST;
            case "WEST" -> Direction.WEST;
            default -> Direction.NONE;
        };
    }
}
