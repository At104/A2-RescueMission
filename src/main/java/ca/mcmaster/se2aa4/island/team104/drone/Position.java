package ca.mcmaster.se2aa4.island.team104.drone;

public class Position {
    private final int x;
    private final int y;
    private PointOfInterest poi;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
        this.poi = null;
    }

   
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public PointOfInterest getPointOfInterest() {
        return poi;
    }

    public void setPointOfInterest(PointOfInterest poi) {
        this.poi = poi;
    }
    public double distanceTo(Position p) {
        return Math.hypot(this.x - p.getX(), this.y - p.getY());
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
} 