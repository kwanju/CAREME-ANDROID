package zangdol.careme.restapi;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.model.Discover;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetDiscover implements RestUtil.OnRestApiListener {

    private OnGetDiscoverListener listener;
    private final String url = Config.SERVERIP + "android/discover/json/getDiscover";

    public interface OnGetDiscoverListener {
        void onGetDiscover(Discover discover);
    }

    public GetDiscover(OnGetDiscoverListener listener, final String idx) {
        this.listener = listener;

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("idx", idx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetDiscover(json2discover(result));
    }

    private Discover json2discover(JSONObject result) {
        Discover discover = new Discover();
        try {
            JSONObject jsonDiscover = result.getJSONObject("discover");
            discover.setIdx(jsonDiscover.getString("idx"));
            discover.setEventDateTime(jsonDiscover.getString("discover_datetime"));
            discover.setEventSpot(jsonDiscover.getString("discovered_spot"));
            discover.setDescription(jsonDiscover.getString("description"));
            discover.setSpeciesCode(jsonDiscover.getString("species_code"));
            discover.setAnimalSex(jsonDiscover.getString("animal_sex"));
            discover.setUrl_picture(jsonDiscover.getString("url_picture"));
            discover.setRegisterDateTime(jsonDiscover.getString("register_datetime"));
            discover.setUserIdx(jsonDiscover.getString("user_idx"));
            discover.setMatchingShelterIdx(jsonDiscover.getString("matching_shelter_idx"));
            discover.setLongitude(jsonDiscover.getString("longitude"));
            discover.setLatitude(jsonDiscover.getString("latitude"));
            discover.setRegisterNickname(jsonDiscover.getString("nickname"));
            discover.setRegisterPhoneNumber(jsonDiscover.getString("phone_number"));
            discover.setShelterName(jsonDiscover.getString("shelterName"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return discover;
    }
}
