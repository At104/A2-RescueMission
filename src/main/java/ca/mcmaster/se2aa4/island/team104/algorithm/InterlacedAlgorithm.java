package ca.mcmaster.se2aa4.island.team104.algorithm;


import ca.mcmaster.se2aa4.island.team104.drone.Drone;
import ca.mcmaster.se2aa4.island.team104.algorithm.states.State;
import ca.mcmaster.se2aa4.island.team104.algorithm.states.interlaced_states.NewStartState;
public class InterlacedAlgorithm extends Algorithm {
    public InterlacedAlgorithm(Drone drone) {
        super(drone);
    }

    @Override
    protected State getStartState(Drone drone) {
        return new NewStartState(drone);
    }

    
} 