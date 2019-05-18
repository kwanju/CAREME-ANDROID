package zangdol.careme.restapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zangdol.careme.Config;
import zangdol.careme.model.Discover;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetDiscoverRecord implements RestUtil.OnRestApiListener {

    private final String URL = Config.SERVERIP+"android/discover/json/getDiscoverRecord";
    private OnGetDiscoverRecordListener listener;

    public interface OnGetDiscoverRecordListener {
        void OnGetDiscoverRecord(ArrayList<Discover> discovers);
    }

    public GetDiscoverRecord(final String user_idx, OnGetDiscoverRecordListener listener) {
        this.listener = listener;
        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("user_idx", user_idx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        Log.d("TEST",result.toString());
        listener.OnGetDiscoverRecord(json2discovers(result));
    }

    private ArrayList<Discover> json2discovers(JSONObject result) {
        ArrayList<Discover> discovers = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonDiscover = list.getJSONObject(i);
                Discover discover = new Discover();
                discover.setAnimalSex(jsonDiscover.getString("animal_sex"));
                discover.setDescription(jsonDiscover.getString("description"));
                discover.setDiscoverDateTime(jsonDiscover.getString("discover_datetime"));
                discover.setDiscoveredSpot(jsonDiscover.getString("discovered_spot"));
                discover.setIdx(jsonDiscover.getString("idx"));
                discover.setMatchingShelterIdx(jsonDiscover.getString("matching_shelter_idx"));
                discover.setRegisterDateTime(jsonDiscover.getString("register_datetime"));
                discover.setSpeciesCode(jsonDiscover.getString("species_code"));
                discover.setUrl_picture(jsonDiscover.getString("url_picture"));
                discover.setUserIdx(jsonDiscover.getString("user_idx"));
                discover.setShelterName(jsonDiscover.getString("shelterName"));

                discovers.add(discover);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return discovers;
    }
}
