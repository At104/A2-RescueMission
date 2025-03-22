package ca.mcmaster.se2aa4.island.team104.states;

import ca.mcmaster.se2aa4.island.team104.drone.Drone;

public class BasicAlgorithm extends Algorithm {
    public BasicAlgorithm(Drone drone) {
        super(drone, new StartState(drone));
    }

    
} 