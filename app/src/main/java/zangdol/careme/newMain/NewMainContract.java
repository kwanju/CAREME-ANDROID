package zangdol.careme.newMain;

import android.app.Activity;

public class NewMainContract {
    public interface Presenter {
        void logout();

        void checkLogin();

        void getSpeciesCode();
    }

    public interface View {
        void changeLoginState(boolean isLogin);

        void moveLoginActivity();

        Activity getActivity();

    }
}
