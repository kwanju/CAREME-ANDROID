package zangdol.careme.model;

import zangdol.careme.presenter.MainController;
import zangdol.careme.util.SaveSharedPreference;

public class MainModel {
    private MainController mainController;

    public MainModel(MainController mainController) {
        this.mainController = mainController;
    }

    public void logout() {
        SaveSharedPreference.clear();
    }
}
