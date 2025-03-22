package ca.mcmaster.se2aa4.island.team104.drone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoordinateMap {
    private int height;
    private int width;
    private Set<Position> visitedPositions;
    private Position startingPosition;
    private List<PointOfInterest> creeks;
    private PointOfInterest site;
    public CoordinateMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.visitedPositions = new HashSet<>();
        this.creeks = new ArrayList<>();
    }

    public void addVisitedPosition(Position pos) {
        visitedPositions.add(pos);
    }

    public boolean hasVisited(Position pos) {
        return visitedPositions.contains(pos);
    }

    public Set<Position> getVisitedPositions() {
        return new HashSet<>(visitedPositions);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setDimensions(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public boolean isValidPosition(Position pos) {
        return pos.getX() >= 0 && pos.getX() < width &&
               pos.getY() >= 0 && pos.getY() < height;
    }

    public boolean isAtMapBoundary(Position pos) {
        return pos.getX() == 0 || pos.getX() == width - 1 ||
               pos.getY() == 0 || pos.getY() == height - 1;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position pos) {
        this.startingPosition = pos;
        addVisitedPosition(pos);
    }

    public boolean isAtStartingPosition(Position pos) {
        return pos.equals(startingPosition);
    }

    public List<PointOfInterest> getCreeks() {
        return creeks;
    }

    public PointOfInterest getSite() {
        return site;
    }

    private void addCreek(PointOfInterest creek) {
        creeks.add(creek);
    }

    private void addSite(PointOfInterest site) {
        this.site = site;
    }

    public void addPointOfInterest(PointOfInterest poi) {
        if (poi.isCreek()) {
            addCreek(poi);
        } 
        else if (poi.isEmergencySite()) {
            addSite(poi);
        }
    }

    public PointOfInterest getClosestCreek() {
        if (creeks.isEmpty()) {
            return null;
        }

        PointOfInterest closestCreek = creeks.get(0);
        int minDistance = site.getPosition().getManhattanDistance(closestCreek.getPosition());

        for (PointOfInterest creek : creeks) {
            int distance = site.getPosition().getManhattanDistance(creek.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                closestCreek = creek;
            }
        }

        return closestCreek;
    }
    
    
}
