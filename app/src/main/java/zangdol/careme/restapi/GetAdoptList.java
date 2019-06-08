package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetAdoptList implements RestUtil.OnRestApiListener {

    private String url = Config.SERVERIP + "android/adopt/json/getAdoptList";
    private OnGetAdoptListListener listener;

    public interface OnGetAdoptListListener {
        void onGetAdoptList(ArrayList<HashMap<String, String>> adopts);
    }

    public GetAdoptList(OnGetAdoptListListener listener, final String idx) {
        this.listener = listener;

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("user_idx", idx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetAdoptList(json2adopts(result));
    }

    private ArrayList<HashMap<String, String>> json2adopts(JSONObject result) {
        ArrayList<HashMap<String, String>> adopts = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject adoptJson = list.getJSONObject(i);
                HashMap<String, String> adopt = new HashMap<>();
                adopt.put("datetime", adoptJson.getString("datetime"));
                adopt.put("animalName", adoptJson.getString("animalName"));
                adopt.put("species_code", adoptJson.getString("species_code"));
                adopt.put("shelterName", adoptJson.getString("shelterName"));
                adopt.put("permit", adoptJson.getString("permit"));
                adopt.put("url_picture", adoptJson.getString("url_picture"));
                adopts.add(adopt);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return adopts;
    }
}
