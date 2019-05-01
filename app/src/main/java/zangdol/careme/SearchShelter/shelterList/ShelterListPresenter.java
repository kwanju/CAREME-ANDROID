package zangdol.careme.SearchShelter.shelterList;

import android.content.Intent;

import java.util.ArrayList;

import zangdol.careme.SearchShelter.ShelterInfoActivity;
import zangdol.careme.model.Shelter;

public class ShelterListPresenter implements ShelterListContract.Presenter{
    private ShelterListActivity activity;

    private ArrayList<Shelter> shelters;

    public ShelterListPresenter(ShelterListActivity activity) {
        this.activity = activity;
        shelters = (ArrayList<Shelter>) activity.getIntent().getSerializableExtra("shelters");
    }


    @Override
    public void addShelters() {
        for(int i=0;i<shelters.size();i++){
            Shelter shelter = shelters.get(i);
            activity.addList(shelter);
        }
    }

    @Override
    public void moveShelter(int position) {
        Shelter shelter = shelters.get(position);

        Intent intent = new Intent( activity, ShelterInfoActivity.class );
        intent.putExtra("idx",shelter.getIdx());
        activity.startActivity( intent );
    }
}
