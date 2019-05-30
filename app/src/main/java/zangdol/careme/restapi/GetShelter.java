package zangdol.careme.restapi;

import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetShelter implements RestUtil.OnRestApiListener {

    private OnGetShelterListener listener;
    private final String URL = Config.SERVERIP + "android/shelter/json/getShelter";

    public interface OnGetShelterListener {
        void onGetShelter(Shelter shelter);
    }

    public GetShelter(final String idx, OnGetShelterListener listener) {
        this.listener = listener;
        RestFactory.getInstance().request(URL, this, new Parameters() {
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
        listener.onGetShelter(json2shelter(result));
    }

    private Shelter json2shelter(JSONObject result) {
        Shelter shelter = new Shelter();
        try {
            JSONObject resultShelter = result.getJSONObject("shelter");
            shelter.setIdx(resultShelter.getString("idx"));
            shelter.setName(resultShelter.getString("name"));
            shelter.setPosition(resultShelter.getString("position"));
            shelter.setPnum(resultShelter.getString("phone_number"));
            shelter.setStartTime(resultShelter.getString("start_time"));
            shelter.setEndTime(resultShelter.getString("end_time"));
            shelter.setDescription(resultShelter.getString("description"));
            shelter.setUrl_picture(resultShelter.getString("url_picture"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shelter;
    }
}
