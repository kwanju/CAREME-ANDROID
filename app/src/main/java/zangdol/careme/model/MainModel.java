package zangdol.careme.model;

import zangdol.careme.main.MainPresenter;
import zangdol.careme.util.SaveSharedPreference;

public class MainModel {
    private MainPresenter mainPresenter;

    public MainModel(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
    }

    public void logout() {
        SaveSharedPreference.clear();
    }
}
