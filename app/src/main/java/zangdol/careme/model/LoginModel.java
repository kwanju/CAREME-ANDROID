package zangdol.careme.model;


import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import zangdol.careme.controller.LoginController;
import zangdol.careme.restapi.RestFacade;

public class LoginModel implements RestAPIListener {

    private final String TAG = "LoginModel";

    private LoginController loginController; // LoginController

    public LoginModel(LoginController lc) {
        this.loginController = lc;
    }

    public void login(String id, String pw) {
        RestFacade restFacade = RestFacade.getInstance();
        restFacade.login(id, pw, this);
    }

    @Override
    public void onResponse(HashMap<String, String> results) {

        final String message;

        if (results.get("result").equals("1"))  // 성공일 때
            message = "로그인 성공";
        else
            message = "로그인 실패";

        loginController.getActivity().runOnUiThread(new Runnable() { // Toast를 띄우기 위해서 runOnUiThead를 사용.
            @Override
            public void run() {
                Toast toast = Toast.makeText(loginController.getActivity(), message, Toast.LENGTH_LONG);
                toast.show();
            }
        });


        for (String key : results.keySet()) {
            Log.d(TAG, "key : " + key + "/ value : " + results.get(key));
        }
    }
}
