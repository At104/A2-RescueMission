package ca.mcmaster.se2aa4.island.team104.drone;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CoordinateMap {
    private int height;
    private int width;
    private List<Position> Pois;
    private boolean hasCreek = false;

    public CoordinateMap(){
        this.height = 0;
        this.width = 0;
        this.Pois = new ArrayList<>();
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addPoi(Position poi) {
        Pois.add(poi);
        if (poi.hasPointOfInterest() && poi.getPointOfInterest() == Position.PointOfInterest.CREEK) {
            hasCreek = true;
        }
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

    public Position getClosestCreek(){
        if (hasCreek){
            Position closestCreek = null;
            int minDistance = Integer.MAX_VALUE;
            for (Position poi : Pois){
                if (poi.getPointOfInterest() == Position.PointOfInterest.CREEK){
                    int distance = Math.abs(poi.getX()) + Math.abs(poi.getY());
                    if (distance < minDistance){
                        minDistance = distance;
                        closestCreek = poi;
                    }
                }
            }
            return closestCreek;
        }
        return null;
    }

    public Set<Position> getPointsOfInterestByType(Position.PointOfInterest poi) {
        Set<Position> result = new HashSet<>();
        for (Position position : Pois) {
            if (position.hasPointOfInterest() && position.getPointOfInterest() == poi) {
                result.add(position);
            }
        }
        return result;
    }

    public List<Position> getPois() { return  Pois; }
}
