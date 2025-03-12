package ca.mcmaster.se2aa4.island.teamXXX;

import org.json.JSONObject;

public interface Action {
    JSONObject makeDecision();
    int getCost();
}