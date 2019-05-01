package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zangdol.careme.Config;

public class GetVolunteerShelterInfo implements RestUtil.OnRestApiListener {

    private OnVolunteerInfoListener listener;

    public interface OnVolunteerInfoListener {
        void onVolunteerInfo(HashMap<String, String> volunteerInfo);
    }

    public GetVolunteerShelterInfo(String idx, OnVolunteerInfoListener listener) {
        this.listener = listener;

        String url = Config.SERVERIP + "android/shelter/animal/json/getVolunteerShelter";
        RestUtil restUtil = new RestUtil();

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);

        params.add(new BasicNameValuePair("idx", idx));

        restUtil.request(url, params, this);
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
