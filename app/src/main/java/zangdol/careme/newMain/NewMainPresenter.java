package zangdol.careme.newMain;

import java.util.HashMap;

import zangdol.careme.model.User;
import zangdol.careme.restapi.GetSpeciesCode;
import zangdol.careme.restapi.Logout;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.SaveSharedPreference;

public class NewMainPresenter implements NewMainContract.Presenter, Logout.OnLogoutListener, GetSpeciesCode.OnGetSpeciesCodeListener {

    private NewMainContract.View view;

    public NewMainPresenter(NewMainContract.View view) {
        this.view = view;
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
            view.changeLoginState(false);
        else  // 로그인이 되어있을 경우
            view.changeLoginState(true);
    }

    @Override
    public void getSpeciesCode() {
        new GetSpeciesCode(this);
    }

    @Override
    public void onGetSpeciesCode(HashMap<String, String> speciesCodes) {
        ConvertManager.species_code = speciesCodes;
    }

    @Override
    public void onLogout() {
        view.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.getActivity().recreate(); // activity 초기화
            }
        });
    }
}
