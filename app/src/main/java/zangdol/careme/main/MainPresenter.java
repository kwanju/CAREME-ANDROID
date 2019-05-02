package zangdol.careme.main;

import zangdol.careme.model.User;
import zangdol.careme.restapi.Logout;
import zangdol.careme.util.SaveSharedPreference;

public class MainPresenter implements MainContract.Presenter, Logout.OnLogoutListener {
    private MainActivity mainActivity;

    public MainPresenter(MainActivity view) {
        mainActivity = view;
    }

    @Override
    public void logout() {
        String idx = SaveSharedPreference.getIdx();
        new User().logout();
        new Logout(idx, this);
    }

    @Override
    public void checkLogin() {
        if (!SaveSharedPreference.isLogin())  // 로그인이 안되어있을 경우
            mainActivity.changeLoginState(false);
        else  // 로그인이 되어있을 경우
            mainActivity.changeLoginState(true);


    }

    @Override
    public void onLogout() {
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.recreate(); // activity 초기화
            }
        });

    }
}
