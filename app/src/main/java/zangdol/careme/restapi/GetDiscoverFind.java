package zangdol.careme.restapi;

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

public class GetDiscoverFind implements RestUtil.OnRestApiListener {

    private OnGetDiscoverFindListener listener;
    private final String url = Config.SERVERIP + "android/discoverFind/json/getDiscoverFind";

    public interface OnGetDiscoverFindListener {
        void onGetDiscover(ArrayList<DiscoverFind> discoverFinds);
    }

    public GetDiscoverFind(OnGetDiscoverFindListener listener) {
        this.listener = listener;
        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 0;
            }

            @Override
            public void setParams() {

            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetDiscover(json2discoverFinds(result));
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
