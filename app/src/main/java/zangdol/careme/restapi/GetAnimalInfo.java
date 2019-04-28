package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.model.Animal;

public class GetAnimalInfo implements RestUtil.OnRestApiListener {


    private RestUtil restUtil;
    private String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalInfo";
    private OnAnimalInfoListener listener;

    public interface OnAnimalInfoListener {
        void OnAnimalInfo(Animal animal);
    }

    public GetAnimalInfo(String animalIdx, OnAnimalInfoListener listener) {
        this.listener = listener;
        restUtil = RestUtil.getInstance();

        List<NameValuePair> params = new ArrayList<NameValuePair>(2);

        params.add(new BasicNameValuePair("animal_idx", animalIdx));

        restUtil.request(URL, params, this);

    }

    @Override
    public void OnResult(JSONObject result) {
        listener.OnAnimalInfo(json2animal(result));
    }

    private Animal json2animal(JSONObject result) {
        Animal animal = new Animal();


        try {
            JSONObject animalInfo = result.getJSONObject("animalInfo");
            animal.setDiscoveredSpot(animalInfo.getString("discover_idx"));
            animal.setEstimatedBirthAge(animalInfo.getString("estimated_birth_age"));
            animal.setIdx(animalInfo.getString("idx"));
            animal.setDescription(animalInfo.getString("description"));
            animal.setName(animalInfo.getString("name"));

            if(animalInfo.getString("sex").equals("null"))
                animal.setSex('O');
            else
                animal.setSex(animalInfo.getString("sex").charAt(0));

            animal.setShelterIdx(animalInfo.getString("shelter_idx"));
            animal.setSpeciesCode(animalInfo.getString("species_code"));
            animal.setUrlPicture(animalInfo.getString("url_picture"));
            return animal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
