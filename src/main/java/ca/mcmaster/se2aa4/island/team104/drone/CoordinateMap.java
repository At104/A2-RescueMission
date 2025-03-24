package ca.mcmaster.se2aa4.island.team104.drone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoordinateMap {
    private int width;
    private int height;
    private Set<Position> visitedPositions;
    private Position startingPosition;
    private List<PointOfInterest> creeks;
    private PointOfInterest site;
    private final Logger logger = LogManager.getLogger();

    public CoordinateMap() {
        this.width = -1;
        this.height = -1;
        this.visitedPositions = new HashSet<>();
        this.creeks = new ArrayList<>();
    }

    public boolean hasWidth() {
        return width != -1;
    }

    public boolean hasHeight() {
        return height != -1;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDimensions(int height, int width) {
        this.height = height;
        this.width = width;
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
        logger.info("Added creek: {} at position: {}", creek.id(), creek.position());
    }

    private void addSite(PointOfInterest site) {
        this.site = site;
        logger.info("Added emergency site: {} at position: {}", site.id(), site.position());
    }

    public void addPointOfInterest(PointOfInterest poi) {
        logger.info("Adding POI of type: {} with ID: {} at position: {}", poi.type(), poi.id(), poi.position());
        if (poi.type() == POIType.CREEK) {
            addCreek(poi);
        } else if (poi.type() == POIType.EMERGENCY_SITE) {
            addSite(poi);
        }
    }

    public boolean hasCreeks() {
        return !creeks.isEmpty();
    }

    public boolean hasSite() {
        return site != null;
    }

    public PointOfInterest getClosestCreek() {
        logger.info("Getting closest creek");
        if (!hasSite()) {
            return null;
        }

        PointOfInterest closestCreek = null;
        double minDistance = Double.MAX_VALUE;

        for (PointOfInterest creek : creeks) {
            double distance = creek.position().distanceTo(site.position());
            if (distance < minDistance) {
                minDistance = distance;
                closestCreek = creek;
            }
        }
        logger.info("Closest creek: {}", closestCreek);
        return closestCreek;
        
    }
}

