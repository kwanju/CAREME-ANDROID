package zangdol.careme.main;

import android.content.Intent;

import zangdol.careme.model.MainModel;
import zangdol.careme.login.LoginActivity;

public class MainPresenter {
    private MainModel mainModel;
    private MainActivity mainActivity;

    public MainPresenter(MainActivity view) {
        mainActivity = view;
        mainModel = new MainModel(this);
    }


    public void moveLogin() {
        Intent intent = new Intent(mainActivity, LoginActivity.class);
        mainActivity.startActivity(intent);
    }

    public void logout() {
        mainModel.logout();
    }
}
