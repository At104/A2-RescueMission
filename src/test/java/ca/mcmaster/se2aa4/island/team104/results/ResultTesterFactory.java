package ca.mcmaster.se2aa4.island.team104.results;

import org.json.JSONArray;
import org.json.JSONObject;

public class ResultTesterFactory {
    public ActionResult initEchoActionResult(int cost, String status, int range, String found){
        JSONObject obj = new JSONObject();
        obj.put("cost", cost);
        obj.put("status", status);

        JSONObject extras = new JSONObject();
        extras.put("range", range);
        extras.put("found", found);
        obj.put("extras", extras);

        return new ActionResult(obj);
    }

    public ActionResult initScanActionResult(int cost, String status){
        JSONObject obj = new JSONObject();
        obj.put("cost", cost);
        obj.put("status", status);

        JSONObject extras = new JSONObject();
        extras.put("creeks", new JSONArray());
        extras.put("biomes", new JSONArray());
        extras.put("sites", new JSONArray());

        obj.put("extras", extras);
        return  new ActionResult(obj);
    }


}
