package ca.mcmaster.se2aa4.island.team104.drone;

public class DroneTestFactory {
    public Drone initDrone(int x, int y, int battery, String direction ) {
        Drone drone = new Drone(new Position(x,y), Direction.directionFromString(direction), battery , new CoordinateMap());

        return drone;
    }

    public Drone initDrone( ) {
        Drone drone = new Drone(new Position(0,0), Direction.EAST, 100 , new CoordinateMap());

        return drone;
    }
}
