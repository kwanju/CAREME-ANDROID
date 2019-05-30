package zangdol.careme.restapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class SearchShelterCategory implements RestUtil.OnRestApiListener {

    private OnResponseListener listener;

    @Override
    public void OnResult(JSONObject result) {
        listener.onResponse(json2ListShelter(result));
    }


    public interface OnResponseListener {
        void onResponse(ArrayList<Shelter> shelterList);
    }

    public void request(final String big, final String small, OnResponseListener listener) {
        String URL = Config.SERVERIP + "android/shelter/json/searchShelter";

        this.listener = listener;


        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 2;
            }

            @Override
            public void setParams() {
                addParam("big", big);
                addParam("small", small);
            }
        });

    }

    private ArrayList<Shelter> json2ListShelter(JSONObject results) {

        try {
            ArrayList<Shelter> shelterList = new ArrayList<>();

            String result = results.getString("result");

            if (result.equals("0")) // 결과가 실패했을 때
                return null;

            JSONArray lists = results.getJSONArray("list");

            for (int i = 0; i < lists.length(); i++) {
                Shelter shelter = new Shelter();
                shelter.setName(lists.getJSONObject(i).getString("name"));
                shelter.setIdx(lists.getJSONObject(i).getString("idx"));
                shelter.setPosition(lists.getJSONObject(i).getString("position"));
                shelter.setPnum(lists.getJSONObject(i).getString("phone_number"));
                shelter.setUrl_picture(lists.getJSONObject(i).getString("url_picture"));


                Log.d("Test",shelter.getName()+" / "+lists.getJSONObject(i).getString("name"));
                shelterList.add(shelter);
            }

            return shelterList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }


}
