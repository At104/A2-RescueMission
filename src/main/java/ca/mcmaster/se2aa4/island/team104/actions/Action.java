package ca.mcmaster.se2aa4.island.team104.actions;

import org.json.JSONObject;

public interface Action {
    JSONObject makeAction();
    //Costs based on values from https://ace-design.github.io/island/actions/
    int getCost();
}