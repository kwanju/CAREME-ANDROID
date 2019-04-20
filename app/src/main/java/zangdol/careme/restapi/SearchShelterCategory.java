package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.Shelter;

public class SearchShelterCategory implements RestUtil.OnRestApiListener{

    private OnResponseListener listener;

    private RestUtil restUtil;
    @Override
    public void OnResult(JSONObject result) {
        listener.onResponse(json2ListShelter(result));
    }


    public interface OnResponseListener{
        void onResponse(List<Shelter> shelterList);
    }

    public void request(String big, String small,OnResponseListener listener) {
        String URL = Config.SERVERIP+"android/shelter/json/searchShelter";

        this.listener = listener;

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        params.add(new BasicNameValuePair("big", big));
        params.add(new BasicNameValuePair("small", small));

        restUtil = RestUtil.getInstance();

        restUtil.request(URL,params,this);

    }

    private List<Shelter> json2ListShelter(JSONObject results){

        try {
            List<Shelter> shelterList = new ArrayList<>();

            String result = results.getString("result");

            if(results.equals("0")) // 결과가 실패했을 때
                return null;

            JSONArray lists = results.getJSONArray("list");

            for(int i=0;i<lists.length();i++){
                Shelter shelter = new Shelter();
                shelter.setName(lists.getJSONObject(i).getString("name"));
                shelterList.add(shelter);
            }

            return shelterList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }



    }


}
