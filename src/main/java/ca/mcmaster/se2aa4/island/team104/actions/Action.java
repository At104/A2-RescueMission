package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;

public interface Action {
    JSONObject makeAction();
    int getCost();
}