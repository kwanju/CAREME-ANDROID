package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.AnimalSummary;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetAnimalListSummaryInShelter implements RestUtil.OnRestApiListener {

    private OnResponseListener listener;

    public interface OnResponseListener {
        void onResponse(List<AnimalSummary> list);
    }

    public void request(final String shelterIdx, final String state, OnResponseListener listener) {
        String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalSummary";

        this.listener = listener;

        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 2;
            }

            @Override
            public void setParams() {
                addParam("shelter_idx", shelterIdx);
                addParam("state", state);
            }
        });
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
