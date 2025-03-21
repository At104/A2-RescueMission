package ca.mcmaster.se2aa4.island.team104.drone;

public enum Direction {

    NORTH, SOUTH, EAST, WEST, NONE;

    public Direction left() {
        return switch (this) {
            case NORTH -> Direction.WEST;
            case SOUTH -> Direction.EAST;
            case EAST -> Direction.NORTH;
            case WEST -> Direction.SOUTH;
            default -> Direction.NONE;
        };
    }

    public Direction right() {
        return switch (this) {
            case NORTH -> Direction.EAST;
            case SOUTH -> Direction.WEST;
            case EAST -> Direction.SOUTH;
            case WEST -> Direction.NORTH;
            default -> Direction.NONE;
        };
    }

    public boolean isValidTurn(Direction newDirection) {
        // Can only turn left or right, no U-turns
        return newDirection == this.left() || newDirection == this.right();
    }

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
            default -> "NONE";
        };
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
