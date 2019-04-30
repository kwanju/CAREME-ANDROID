package zangdol.careme.model;


import android.util.Log;

import java.util.HashMap;

import zangdol.careme.restapi.RestFacade;
import zangdol.careme.util.SaveSharedPreference;

public class User implements zangdol.careme.restapi.Login.OnLoginListener {

    private final String TAG = "User";
    
    private OnLoginListener loginListener;

    @Override
    public void onLogin(HashMap<String, String> results) {
        if (results.get("result").equals("1")) {// 성공일 때
            loginListener.onLogin(true);
            setSession(results);
        } else
            loginListener.onLogin(false);
    }


    // 로그인이 성공했을 때 적용해야하는 부분
    public interface OnLoginListener {
        void onLogin(boolean isSuccess);
    }

    //로그인 기능 부분
    public void login(String id, String pw) {
        RestFacade restFacade = RestFacade.getInstance();
        restFacade.login(id, pw, this);
    }

    //로그아웃 부분
    public void logout() {
        SaveSharedPreference.clear();
    }


    public void setLoginListener(OnLoginListener loginListener) {
        this.loginListener = loginListener;
    }

    // 세션을 설정하는 함수.
    public static void setSession(HashMap<String, String> results){
        SaveSharedPreference.setUser(results.get("id"), results.get("idx"),results.get("nickname")); // 사용자 로그인정보 저장.
    }


}
