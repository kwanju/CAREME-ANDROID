package zangdol.careme.discoverFindRecord;

import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.model.DiscoverFind;
import zangdol.careme.restapi.GetDiscoverRecord;
import zangdol.careme.util.SaveSharedPreference;

public class DiscoverFindRecordPresenter implements DiscoverFindRecordContract.Presenter, GetDiscoverRecord.OnGetDiscoverRecordListener {
    private DiscoverFindRecordContract.View view;

    public DiscoverFindRecordPresenter(DiscoverFindRecordContract.View view) {
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
    public void OnGetDiscoverRecord(ArrayList<DiscoverFind> discoverFinds) {
        view.setAdapter(new DiscoverFindRecordAdapter(view.getActivity(), discoverFinds));
    }
}
