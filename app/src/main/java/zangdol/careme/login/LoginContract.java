package zangdol.careme.login;

public class LoginContract {
    public interface Presenter {
        void login(String id,String pw);

    }
    public interface View{
        void moveMainActivity();
    }
}
