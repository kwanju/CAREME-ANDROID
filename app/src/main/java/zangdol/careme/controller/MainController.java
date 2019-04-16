package zangdol.careme.controller;

import android.content.Intent;

import zangdol.careme.model.MainModel;
import zangdol.careme.view.LoginActivity;
import zangdol.careme.view.MainActivity;

public class MainController {
    private MainModel mainModel;
    private MainActivity mainActivity;

    public MainController(MainActivity view) {
        mainActivity = view;
        mainModel = new MainModel(this);
    }


    public void moveLogin() {
        Intent intent = new Intent(mainActivity, LoginActivity.class);
        mainActivity.startActivity(intent);
    }
}
