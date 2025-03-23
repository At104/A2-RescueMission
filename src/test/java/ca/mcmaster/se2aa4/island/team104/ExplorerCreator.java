package ca.mcmaster.se2aa4.island.team104;

import org.json.JSONObject;

public class ExplorerCreator {

    public Explorer initExplorer(){
        Explorer explorer = new Explorer();

        // Set the parameters of the explorer
        JSONObject info = new JSONObject();
        info.put("heading", "EAST");
        info.put("budget", 7000);
        explorer.initialize(info.toString());

        return explorer;
    }
}
