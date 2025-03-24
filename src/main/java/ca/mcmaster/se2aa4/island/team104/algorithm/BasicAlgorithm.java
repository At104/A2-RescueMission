package ca.mcmaster.se2aa4.island.team104.algorithm;

import ca.mcmaster.se2aa4.island.team104.algorithm.states.basic_states.StartState;
import ca.mcmaster.se2aa4.island.team104.algorithm.states.State;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;

public class BasicAlgorithm extends Algorithm {
    public BasicAlgorithm(Drone drone) {
        super(drone);
    }

    @Override
    protected State getStartState(Drone drone) {
        return new StartState(drone);
    }

    
} 