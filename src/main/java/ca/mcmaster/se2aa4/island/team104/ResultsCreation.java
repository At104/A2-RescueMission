package ca.mcmaster.se2aa4.island.team104;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

public class ResultsCreation {
    private boolean foundLand = false;
    private boolean foundGround = false;
    private boolean foundNull = false;
    private boolean foundCreek = false;
    private List<Integer> creeksLocationsX = new ArrayList<Integer>();
    private List<Integer> creeksLocationsY = new ArrayList<Integer>();
    private List<String> creekIDs = new ArrayList<String>();
    private int emergencySiteX = 0;
    private int emergencySiteY = 0;
    private JSONObject extraInfo = new JSONObject();

    public void initExtraInfo(JSONObject newInfo) {
        if (extraInfo == null){
            extraInfo = new JSONObject();
        }else {
            extraInfo = newInfo.getJSONObject("extraInfo");
        }
    }

    public boolean outOfBounds(){
        boolean retVal = false;
        if(extraInfo.has("found")){
            retVal = "OUT_OF_RANGE".equals(extraInfo.getString("found"));
        }else{
            retVal = false;
        }
        if(retVal && this.distance() < 2){
            retVal = true;
        }else if(retVal){
            retVal = false;
        }
        return retVal;
    }

    public boolean isFoundGround(){
        if(extraInfo.has("biomes")){
            JSONArray biomes = extraInfo.getJSONArray("biomes");
            if(biomes.isEmpty()){
                return false;
            } else {
                for(int i = 0; i < biomes.length(); i++){
                    if(biomes.getString(i).equals("OCEAN")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isFoundCreek(){
        if(extraInfo.has("creeks")) {
            JSONArray creeks = extraInfo.getJSONArray("creeks");
            if(creeks.isEmpty()){
                return false;
            } else {
                for(int i = 0; i < creeks.length(); i++){
                    creekIDs.add(creeks.getString(i));
                }
                return true;
            }

        }
        return false;
    }

    public int distance(){
        return extraInfo.optInt("range", 0);
    }

    public boolean isFoundEmergencySite(){
        if(extraInfo.has("sites")){
            JSONArray sites = extraInfo.getJSONArray("sites");
            return !sites.isEmpty();
        }
        return false;
    }

    public void getCreekCoordinates(int x, int y){
        creeksLocationsX.add(x);
        creeksLocationsY.add(y);

    }

    public void getEmergencySiteCoordinates(int x, int y){
        emergencySiteX = x;
        emergencySiteY = y;
    }

    //TODO Add get closest creek method.
}
