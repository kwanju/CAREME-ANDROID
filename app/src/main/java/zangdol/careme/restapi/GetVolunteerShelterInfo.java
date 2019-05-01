package zangdol.careme.restapi;

import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetVolunteerShelterInfo implements RestUtil.OnRestApiListener {

    private OnVolunteerInfoListener listener;

    public interface OnVolunteerInfoListener {
        void onVolunteerInfo(HashMap<String, String> volunteerInfo);
    }

    public GetVolunteerShelterInfo(final String idx, OnVolunteerInfoListener listener) {
        this.listener = listener;

        String url = Config.SERVERIP + "android/shelter/animal/json/getVolunteerShelter";

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
        try {
            listener.onVolunteerInfo(RestUtil.json2map(result.getJSONObject("volunteer")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
