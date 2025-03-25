package ca.mcmaster.se2aa4.island.team104;

import ca.mcmaster.se2aa4.island.team104.drone.CoordinateMap;
import ca.mcmaster.se2aa4.island.team104.drone.Direction;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.drone.Position;

public class DroneTestFactory {
    public Drone initDrone(int x, int y, int battery, String direction) {
        Drone drone = new Drone(new Position(x,y), Direction.fromShortName(direction), battery, new CoordinateMap());
        return drone;
    }

    public Drone initDrone() {
        Drone drone = new Drone(new Position(0,0), Direction.fromShortName("E"), 100, new CoordinateMap());
        return drone;
    }
}
