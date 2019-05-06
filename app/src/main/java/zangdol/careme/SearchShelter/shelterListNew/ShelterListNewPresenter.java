package zangdol.careme.SearchShelter.shelterListNew;

import android.content.Intent;

import java.util.ArrayList;

import zangdol.careme.model.Shelter;
import zangdol.careme.shelter.ShelterInfoActivity;

public class ShelterListNewPresenter implements ShelterListNewContract.Presenter {
    private ShelterListNewContract.View view;
    private ArrayList<Shelter> shelters;

    public ShelterListNewPresenter(ShelterListNewContract.View view) {
        this.view = view;
        shelters = (ArrayList<Shelter>) view.getActivity().getIntent().getSerializableExtra("shelters");
    }


    @Override
    public ShelterListNewAdapter getAdapter() {
        return new ShelterListNewAdapter(shelters, view.getActivity());
    }

    @Override
    public void moveShelter(int position) {
        Shelter shelter = shelters.get(position);
        Intent intent = new Intent( view.getActivity(), ShelterInfoActivity.class );
        intent.putExtra("idx",shelter.getIdx());
        view.getActivity().startActivity( intent );
    }
}
