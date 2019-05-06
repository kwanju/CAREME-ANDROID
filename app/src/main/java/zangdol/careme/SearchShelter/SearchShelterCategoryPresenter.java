package zangdol.careme.SearchShelter;

import android.content.Intent;

import java.util.ArrayList;

import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.SearchShelterCategory;
import zangdol.careme.SearchShelter.shelterListNew.ShelterListNewActivity;

public class SearchShelterCategoryPresenter implements SearchShelterCategoryContract.Presenter, SearchShelterCategory.OnResponseListener {

    private SearchShelterCategoryActivity activity;

    public SearchShelterCategoryPresenter(SearchShelterCategoryActivity activity) {
        this.activity = activity;
    }

    @Override
    public void searchShelter(String big, String small) {
        new SearchShelterCategory().request(big, small, this);
    }

    @Override
    public void onResponse(final ArrayList<Shelter> shelterList) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, ShelterListNewActivity.class);
                intent.putExtra("shelters", shelterList);
                activity.startActivity(intent);
            }
        });

    }
}
