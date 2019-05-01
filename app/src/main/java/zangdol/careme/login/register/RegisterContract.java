package zangdol.careme.login.register;

public class RegisterContract {
    public interface View {

    }

    public interface Presenter {
        void checkDupId(String id);

        void register(String id, String pw, String nickname, String pnum, String email);

        void setCheckDupIDForFalse();
    }
}
