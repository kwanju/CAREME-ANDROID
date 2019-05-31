package zangdol.careme.restapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import zangdol.careme.Config;
import zangdol.careme.model.Discover;
import zangdol.careme.model.DiscoverFind;
import zangdol.careme.model.Find;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetDiscoverRecord implements RestUtil.OnRestApiListener {

    private final String URL = Config.SERVERIP+"android/discoverFind/json/getDiscoverFindRecord";
    private OnGetDiscoverRecordListener listener;

    public interface OnGetDiscoverRecordListener {
        void OnGetDiscoverRecord(ArrayList<DiscoverFind> discovers);
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
        listener.OnGetDiscoverRecord(json2discoverFinds(result));
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
                discover.setEventDateTime(jsonDiscover.getString("discover_datetime"));
                discover.setEventSpot(jsonDiscover.getString("discovered_spot"));
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

    private ArrayList<DiscoverFind> json2discoverFinds(JSONObject result) {
        ArrayList<DiscoverFind> discoverFinds = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject discoverFind = list.getJSONObject(i);

                DiscoverFind df;
                if (isDiscoverJSONObject(discoverFind)){
                    df = new Discover();
                    df.setIdx(discoverFind.getString("idx"));
                    df.setSpeciesCode(discoverFind.getString("species_code"));
                    df.setAnimalSex(discoverFind.getString("animal_sex"));
                    df.setUrl_picture(discoverFind.getString("url_picture"));
                    df.setEventSpot(discoverFind.getString("discovered_spot"));
                    df.setEventDateTime(discoverFind.getString("discover_datetime"));
                    ((Discover) df).setMatchingShelterIdx(discoverFind.getString("matching_shelter_idx"));
                    ((Discover) df).setShelterName(discoverFind.getString("shelterName"));
                }
                else{
                    df = new Find();
                    df.setIdx(discoverFind.getString("idx"));
                    df.setSpeciesCode(discoverFind.getString("species_code"));
                    df.setAnimalSex(discoverFind.getString("animal_sex"));
                    df.setUrl_picture(discoverFind.getString("url_picture"));
                    df.setEventSpot(discoverFind.getString("lost_spot"));
                    df.setEventDateTime(discoverFind.getString("lost_datetime"));
                }
                discoverFinds.add(df);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return discoverFinds;
    }

    private boolean isDiscoverJSONObject(JSONObject discoverFind) throws JSONException {
        if (discoverFind.getString("code").equals("0"))
            return true;
        else
            return false;
    }
}
