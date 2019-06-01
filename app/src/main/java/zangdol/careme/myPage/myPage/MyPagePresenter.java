package zangdol.careme.myPage.myPage;

import java.util.HashMap;

import zangdol.careme.restapi.GetMyInfo;
import zangdol.careme.util.SaveSharedPreference;

public class MyPagePresenter implements MyPageContract.Presenter, GetMyInfo.OnGetMyInfoListener {
    private MyPageContract.View view;

    public MyPagePresenter(MyPageContract.View view) {
        this.view = view;
    }

    @Override
    public void getData() {
        if (!SaveSharedPreference.isLogin())
            return;
        String idx = SaveSharedPreference.getIdx();
        new GetMyInfo(this, idx);
    }

    @Override
    public void onGetMyInfo(HashMap<String, String> myinfo) {
        view.setData(myinfo);
    }
}
