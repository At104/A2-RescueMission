package ca.mcmaster.se2aa4.island.team104.algo.basicalgorithm;

import ca.mcmaster.se2aa4.island.team104.algo.Algorithm;
import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.states.StartState;
import ca.mcmaster.se2aa4.island.team104.states.State;

public class BasicAlgorithm extends Algorithm {
    public BasicAlgorithm(Drone drone) {
        super(drone);
    }

    @Override
    protected State getStartState(Drone drone) {
        return new StartState(drone);
    }

    
} 