package zangdol.careme.main;

public class MainContract {
    public interface Presenter{
        void moveLogin();
        void logout();
        void checkLogin();

    }
    public interface View{
        void changeLoginState(boolean isLogin);
    }
}
