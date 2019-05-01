package zangdol.careme.login;

public class LoginContract {
    public interface Presenter {
        void login(String id,String pw);
        void register();

    }
    public interface View{
        void moveMainActivity();
    }
}
