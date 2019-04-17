package zangdol.careme.model;


import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import zangdol.careme.login.LoginPresenter;
import zangdol.careme.restapi.RestFacade;
import zangdol.careme.util.SaveSharedPreference;

public class LoginModel implements RestAPIListener {

    private final String TAG = "LoginModel";

    private LoginPresenter loginPresenter; // LoginPresenter

    public LoginModel(LoginPresenter lc) {
        this.loginPresenter = lc;
    }

    public void login(String id, String pw) {
        RestFacade restFacade = RestFacade.getInstance();
        restFacade.login(id, pw, this);
    }

    @Override
    public void onResponse(HashMap<String, String> results) {

        final String message;

        if (results.get("result").equals("1")){// 성공일 때
            message = "로그인 성공";
            SaveSharedPreference.setUser(results.get("id"),results.get("idx")); // 사용자 로그인정보 저장.
        }
        else
            message = "로그인 실패";

        loginPresenter.getActivity().runOnUiThread(new Runnable() { // Toast를 띄우기 위해서 runOnUiThead를 사용.
            @Override
            public void run() {
                Toast toast = Toast.makeText(loginPresenter.getActivity(), message, Toast.LENGTH_LONG);
                toast.show();
            }
        });


        for (String key : results.keySet()) {
            Log.d(TAG, "key : " + key + "/ value : " + results.get(key));
        }
    }
}
