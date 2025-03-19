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
    private static final int MAX_GRID_SIZE = 100;

    public CoordinateSystem(int x, int y, Direction currentDirection) {
        this.currentPosition = new Position(x, y);
        this.currentDirection = currentDirection;
        this.visitedPositions = new HashSet<>();
        this.pointsOfInterest = new HashMap<>();
        this.visitedPositions.add(currentPosition);
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
        return pos.getX() >= 0 && pos.getX() < MAX_GRID_SIZE && 
               pos.getY() >= 0 && pos.getY() < MAX_GRID_SIZE;
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

    public void updateBiome(Position pos, Position.Biome biome) {
        pos.setBiome(biome);
    }

    public void addPointOfInterest(Position pos, Position.PointOfInterest poi) {
        pos.setPointOfInterest(poi);
        pointsOfInterest.put(poi, pos);
    }

    public Position getNearestPointOfInterest(Position.PointOfInterest poi) {
        return pointsOfInterest.get(poi);
    }

    public Set<Position> getAllPointsOfInterest() {
        return new HashSet<>(pointsOfInterest.values());
    }

    public Set<Position> getPointsOfInterestByType(Position.PointOfInterest poi) {
        Set<Position> result = new HashSet<>();
        for (Map.Entry<Position.PointOfInterest, Position> entry : pointsOfInterest.entrySet()) {
            if (entry.getKey() == poi) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    public Position getNearestCreek() {
        Position nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Position creek : getPointsOfInterestByType(Position.PointOfInterest.CREEK)) {
            double distance = getDistanceTo(creek);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = creek;
            }
        }
        return nearest;
    }
}
