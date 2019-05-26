package zangdol.careme.restapi;

import org.json.JSONException;
import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.model.Find;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetFind implements RestUtil.OnRestApiListener {

    private OnGetFindListener listener;
    private final String url = Config.SERVERIP + "android/find/json/getFind";

    public interface OnGetFindListener {
        void onGetFind(Find find);
    }

    public GetFind(OnGetFindListener listener, final String idx) {
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
        listener.onGetFind(json2find(result));
    }

    private Find json2find(JSONObject result) {
        Find find = new Find();

        try {
            JSONObject jsonFind = result.getJSONObject("find");
            find.setIdx(jsonFind.getString("idx"));
            find.setUserIdx(jsonFind.getString("user_idx"));
            find.setEventDateTime(jsonFind.getString("lost_datetime"));
            find.setEventSpot(jsonFind.getString("lost_spot"));
            find.setDescription(jsonFind.getString("description"));
            find.setSpeciesCode(jsonFind.getString("species_code"));
            find.setAnimalSex(jsonFind.getString("animal_sex"));
            find.setUrl_picture(jsonFind.getString("url_picture"));
            find.setRegisterDateTime(jsonFind.getString("register_datetime"));
            find.setLongitude(jsonFind.getString("longitude"));
            find.setLatitude(jsonFind.getString("latitude"));
            find.setRegisterNickname(jsonFind.getString("nickname"));
            find.setRegisterPhoneNumber(jsonFind.getString("phone_number"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return find;
    }
}