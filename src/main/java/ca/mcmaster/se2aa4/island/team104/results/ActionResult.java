package ca.mcmaster.se2aa4.island.team104.results;


import org.json.JSONObject;
public class ActionResult {

    private int cost;
    private boolean status;
    private EchoActionResult echoResult;
    private ScanActionResult scanResult;

    public ActionResult(JSONObject json) {
        this.cost = json.getInt("cost");
        this.status = json.getString("status").equals("OK");
        JSONObject extras = json.getJSONObject("extras");

        if (extras.has("biomes")) {
            scanResult = new ScanActionResult(
                extras.getJSONArray("biomes").toList().stream().map(Object::toString).toList(),
                extras.getJSONArray("creeks").toList().stream().map(Object::toString).toList(),
                extras.getJSONArray("sites").toList().stream().map(Object::toString).toList()
            );
        }
        else if (extras.has("range")) {
            echoResult = new EchoActionResult(
                extras.getInt("range"),
                extras.getString("found").equals("GROUND")
            );
        }
    }

    @Override
    public String toString() {
        String result = "";
        JSONObject json = new JSONObject();
        json.put("cost", cost);
        if (echoResult != null) {
            result = echoResult.toString();
        }
        else if (scanResult != null) {
            result = scanResult.toString();
        }
        json.put("extras", result);
        json.put("status", status ? "OK" : "FAIL");

        return json.toString();
    }

    public int getCost() {
        return cost;
    }
}
