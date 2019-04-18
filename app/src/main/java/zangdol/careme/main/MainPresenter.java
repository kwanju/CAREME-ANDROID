package zangdol.careme.main;

import zangdol.careme.model.Login;
import zangdol.careme.util.SaveSharedPreference;

public class MainPresenter implements MainContract.Presenter {
    private MainActivity mainActivity;

    public MainPresenter(MainActivity view) {
        mainActivity = view;
    }

    @Override
    public void logout() {
        new Login().logout();
        mainActivity.recreate(); // activity 초기화
    }

    @Override
    public void checkLogin() {
        if (SaveSharedPreference.getID().length() == 0)  // 로그인이 안되어있을 경우
            mainActivity.changeLoginState(false);
        else  // 로그인이 되어있을 경우
            mainActivity.changeLoginState(true);


    }
}
