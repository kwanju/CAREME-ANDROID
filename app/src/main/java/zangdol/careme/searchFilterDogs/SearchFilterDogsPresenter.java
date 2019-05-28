package zangdol.careme.searchFilterDogs;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.model.FoundAnimal;
import zangdol.careme.restapi.FindAnimal;

public class SearchFilterDogsPresenter implements SearchFilterDogsContract.Presenter, FindAnimal.OnFindAnimalListener {

    private SearchFilterDogsContract.View view;
    private HashMap<String, String> data;

    public SearchFilterDogsPresenter(SearchFilterDogsContract.View view) {
        this.view = view;
        data = new HashMap<>();
    }

    @Override
    public void search() {
        Log.d("SEARCH", data.toString());
        new FindAnimal(this, data);
    }

    @Override
    public void setData(String key, String value) {
        data.put(key, value);
    }

    @Override
    public void onFindAnimal(ArrayList<FoundAnimal> foundAnimals) {
        view.setAdapter(new DogFilterAdapter(foundAnimals, view.getActivity()));
    }
}
