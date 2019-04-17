package zangdol.careme.presenter;


import android.app.Activity;

import zangdol.careme.model.LoginModel;
import zangdol.careme.view.LoginActivity;

public class LoginController {
    private LoginModel loginModel;
    private LoginActivity loginActivity;

    public LoginController(LoginActivity view) {
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
