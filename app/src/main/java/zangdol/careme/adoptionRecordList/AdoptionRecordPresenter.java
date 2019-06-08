package zangdol.careme.adoptionRecordList;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.restapi.GetAdoptList;
import zangdol.careme.util.SaveSharedPreference;

public class AdoptionRecordPresenter implements AdoptionRecordContract.Presenter, GetAdoptList.OnGetAdoptListListener {

    private AdoptionRecordContract.View view;

    public AdoptionRecordPresenter(AdoptionRecordContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        if (!SaveSharedPreference.isLogin())
            return;
        new GetAdoptList(this, SaveSharedPreference.getIdx());
    }

    @Override
    public void onGetAdoptList(ArrayList<HashMap<String, String>> adopts) {
        view.setAdapter(new AdoptionRecordAdapter(adopts, view.getActivity()));
    }
}
