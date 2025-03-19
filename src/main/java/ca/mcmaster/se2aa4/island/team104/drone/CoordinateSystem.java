package ca.mcmaster.se2aa4.island.team104.drone;

import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class CoordinateSystem {
    private Position currentPosition;
    private Direction currentDirection;
    private Set<Position> visitedPositions;
    private Map<Position.PointOfInterest, Position> pointsOfInterest;
    private CoordinateMap map;

    public CoordinateSystem(int x, int y, Direction currentDirection) {
        this.currentPosition = new Position(x, y);
        this.currentDirection = currentDirection;
        this.visitedPositions = new HashSet<>();
        this.pointsOfInterest = new HashMap<>();
        this.visitedPositions.add(currentPosition);
        this.map = new CoordinateMap();
    }

    public boolean moveForward() {
        Position nextPosition = getPositionInDirection(currentDirection);
        
        if (isValidPosition(nextPosition)) {
            currentPosition = nextPosition;
            visitedPositions.add(currentPosition);
            return true;
        }
        return false;
    }

    public void turnLeft() {
        currentDirection = currentDirection.left();
    }

    public void turnRight() {
        currentDirection = currentDirection.right();
    }

    public boolean isValidPosition(Position pos) {
        return pos.getX() >= 0 && pos.getX() < map.getWidth() &&
               pos.getY() >= 0 && pos.getY() < map.getHeight();
    }

    public boolean hasVisited(Position pos) {
        return visitedPositions.contains(pos);
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position getPositionInDirection(Direction dir) {
        int newX = currentPosition.getX();
        int newY = currentPosition.getY();
        
        switch (dir) {
            case NORTH:
                newY++;
                break;
            case EAST:
                newX++;
                break;
            case SOUTH:
                newY--;
                break;
            case WEST:
                newX--;
                break;
        }
        return new Position(newX, newY);
    }

    public Position getPositionInFront() {
        return getPositionInDirection(currentDirection);
    }

    public Position getPositionToLeft() {
        return getPositionInDirection(currentDirection.left());
    }

    public Position getPositionToRight() {
        return getPositionInDirection(currentDirection.right());
    }

    public double getDistanceTo(Position other) {
        return Math.sqrt(
            Math.pow(other.getX() - currentPosition.getX(), 2) + 
            Math.pow(other.getY() - currentPosition.getY(), 2)
        );
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Set<Position> getVisitedPositions() {
        return new HashSet<>(visitedPositions);
    }
}
