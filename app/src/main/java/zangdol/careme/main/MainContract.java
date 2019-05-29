package zangdol.careme.main;

public class MainContract {
    public interface Presenter{
        void logout();
        void checkLogin();
        void getSpeciesCode();
    }
    public interface View{
        void changeLoginState(boolean isLogin);
        void moveLoginActivity();
    }
}
