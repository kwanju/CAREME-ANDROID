package zangdol.careme.login;


import android.app.Activity;

import zangdol.careme.model.LoginModel;

public class LoginPresenter {
    private LoginModel loginModel;
    private LoginActivity loginActivity;

    public LoginPresenter(LoginActivity view) {
        loginActivity = view;
        loginModel = new LoginModel(this);
    }

    /*
     * 로그인 버튼 눌렸을 때 실행되는 과정
     * */
    public void login(String id, String pw) {

        loginModel.login(id, pw);
    }

    public Activity getActivity() {
        return loginActivity;
    }
}
