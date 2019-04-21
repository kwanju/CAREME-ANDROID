package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.AnimalSummary;

public class GetAnimalListSummaryInShelter implements RestUtil.OnRestApiListener {

    private OnResponseListener listener;
    private RestUtil restUtil;

    public interface OnResponseListener {
        void onResponse(List<AnimalSummary> list);
    }

    public void request(String shelterIdx, String state, OnResponseListener listener) {
        String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalSummary";

        this.listener = listener;

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        params.add(new BasicNameValuePair("shelter_idx", shelterIdx));
        params.add(new BasicNameValuePair("state", state));

        restUtil = RestUtil.getInstance();

        restUtil.request(URL, params, this);
    }

    private List<AnimalSummary> json2ListAnimalSummary(JSONObject results) {

        try {
            List<AnimalSummary> animalSummaryList = new ArrayList<>();
            String result = results.getString("result");

            if (result.equals("0")) // 결과가 실패했을 때
                return null;

            JSONArray lists = results.getJSONArray("list");

            for (int i = 0; i < lists.length(); i++) {
                JSONObject element = lists.getJSONObject(i);
                AnimalSummary animalSummary = new AnimalSummary(
                        element.getString("url_picture"),
                        element.getString("name"),
                        element.getString("idx"),
                        element.getString("species_code")
                );
                animalSummaryList.add(animalSummary);
            }
            return animalSummaryList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void OnResult(JSONObject result) {
        listener.onResponse(json2ListAnimalSummary(result));
    }
}
