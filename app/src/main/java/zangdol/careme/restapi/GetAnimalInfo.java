package zangdol.careme.restapi;

import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.model.Animal;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetAnimalInfo implements RestUtil.OnRestApiListener {


    private RestUtil restUtil;
    private String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalInfo";
    private OnAnimalInfoListener listener;

    public interface OnAnimalInfoListener {
        void OnAnimalInfo(Animal animal);
    }

    public GetAnimalInfo(final String animalIdx, OnAnimalInfoListener listener) {
        this.listener = listener;

        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 2;
            }

            @Override
            public void setParams() {
                addParam("animal_idx", animalIdx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.OnAnimalInfo(json2animal(result));
    }

    private Animal json2animal(JSONObject result) {
        Animal animal = new Animal();


        try {
            JSONObject animalInfo = result.getJSONObject("animalInfo");
            animal.setDiscoveredSpot(animalInfo.getString("discovered_spot"));
            animal.setEstimatedBirthAge(animalInfo.getString("estimated_birth_age"));
            animal.setIdx(animalInfo.getString("idx"));
            animal.setDescription(animalInfo.getString("description"));
            animal.setName(animalInfo.getString("name"));

            if (animalInfo.getString("sex").equals("null"))
                animal.setSex('O');
            else
                animal.setSex(animalInfo.getString("sex").charAt(0));

            animal.setShelterIdx(animalInfo.getString("shelter_name"));
            animal.setSpeciesCode(animalInfo.getString("species_code"));
            animal.setUrlPicture(animalInfo.getString("url_picture"));
            return animal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
