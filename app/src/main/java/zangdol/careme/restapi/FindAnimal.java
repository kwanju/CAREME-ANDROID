package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.model.FoundAnimal;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class FindAnimal implements RestUtil.OnRestApiListener {

    private String url = Config.SERVERIP + "android/discoverFind/json/findAnimal";
    private OnFindAnimalListener listener;

    public interface OnFindAnimalListener {
        void onFindAnimal(ArrayList<FoundAnimal> foundAnimals);
    }

    public FindAnimal(OnFindAnimalListener listener, final HashMap<String, String> data) {
        this.listener = listener;
        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 7;
            }

            @Override
            public void setParams() {
                addParam("start_date", data.get("start_date"));
                addParam("end_date", data.get("end_date"));
                addParam("latitude", data.get("latitude"));
                addParam("longitude", data.get("longitude"));
                addParam("species_code", data.get("species_code"));
                addParam("sex", data.get("sex"));
                addParam("distance", data.get("distance"));
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onFindAnimal(json2foundAnimals(result));
    }

    private ArrayList<FoundAnimal> json2foundAnimals(JSONObject result) {
        ArrayList<FoundAnimal> foundAnimals = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");

            for (int i = 0; i < list.length(); i++) {
                JSONObject animal = list.getJSONObject(i);
                FoundAnimal foundAnimal = new FoundAnimal();

                foundAnimal.setCodeType(animal.getString("code").equals("0") ? FoundAnimal.CodeType.DISCOVER : FoundAnimal.CodeType.SHELTER);
                foundAnimal.setSpecies_code(animal.getString("species_code"));
                foundAnimal.setDate(animal.getString("datetime"));
                foundAnimal.setSpot(animal.getString("spot"));
                foundAnimal.setPicture(animal.getString("url_picture"));
                foundAnimal.setSex(animal.getString("sex"));
                foundAnimal.setIdx(animal.getString("idx"));

                if (foundAnimal.getCodeType() == FoundAnimal.CodeType.SHELTER) {
                    foundAnimal.setShelter_idx(animal.getString("shelter_idx"));
                    foundAnimal.setShelter_name(animal.getString("shelterName"));
                }
                foundAnimals.add(foundAnimal);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return foundAnimals;
    }
}
