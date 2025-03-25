package ca.mcmaster.se2aa4.island.team104.drone;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoordinateMap {
    private int width;
    private int height;
    private Position startingPosition;
    private List<PointOfInterest> creeks;
    private PointOfInterest site;
    private final Logger logger = LogManager.getLogger();

    public CoordinateMap() {
        this.width = -1;
        this.height = -1;
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

    public int getHeight() {
        return height;
    }

    public void setDimensions(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position pos) {
        this.startingPosition = pos;
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

