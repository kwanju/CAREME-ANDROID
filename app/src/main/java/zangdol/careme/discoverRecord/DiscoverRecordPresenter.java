package zangdol.careme.discoverRecord;

import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.model.Discover;
import zangdol.careme.restapi.GetDiscoverRecord;
import zangdol.careme.util.SaveSharedPreference;

public class DiscoverRecordPresenter implements DiscoverRecordContract.Presenter, GetDiscoverRecord.OnGetDiscoverRecordListener {
    private DiscoverRecordContract.View view;

    public DiscoverRecordPresenter(DiscoverRecordContract.View view) {
        this.view = view;
    }

    @Override
    public void getDiscoverRecord() {
        if (SaveSharedPreference.isLogin())
            new GetDiscoverRecord(SaveSharedPreference.getIdx(), this);
        else
            Toast.makeText(view.getActivity(), "로그인이 필요합니다.", Toast.LENGTH_SHORT);
    }

    @Override
    public void OnGetDiscoverRecord(ArrayList<Discover> discovers) {
        view.setAdapter(new DiscoverRecordAdapter(view.getActivity(), discovers));
    }
}
