package zangdol.careme.shelter;

import android.content.Intent;

import zangdol.careme.SearchShelter.AnimalList.AnimalListActivity;
import zangdol.careme.model.Shelter;
import zangdol.careme.restapi.GetShelter;

public class ShelterInfoPresenter implements ShelterInfoContract.Presenter, GetShelter.OnGetShelterListener {
    private ShelterInfoActivity activity;
    private String shelterIdx;

    public ShelterInfoPresenter(ShelterInfoActivity activity) {
        this.activity = activity;
        shelterIdx = activity.getIntent().getStringExtra("idx");
    }

    @Override
    public void getShelter() {
        new GetShelter(shelterIdx, this);
    }

    @Override
    public void moveAnimalList() {
        Intent intent = new Intent(activity, AnimalListActivity.class);
        intent.putExtra("idx", shelterIdx);
        activity.startActivity(intent);
    }

    @Override
    public void onGetShelter(Shelter shelter) {
        activity.setShelter(shelter);
    }
}
