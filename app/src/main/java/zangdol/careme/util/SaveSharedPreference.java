package zangdol.careme.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import zangdol.careme.main.MainActivity;

/*
 * 자동 로그인 기능을 만들기 위해서 로그인 값들을 저장시킴.
 * */
public class SaveSharedPreference {

    static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
    }

    //로그인 성공시 로그인 정보 저장
    public static void setUser(String id, String idx, String nickname) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("id", id);
        editor.putString("idx", idx);
        editor.putString("nickname", nickname);
        editor.commit();
    }

    public static String getID() {
        return getSharedPreferences().getString("id", "");
    }

    public static String getIdx() {
        return getSharedPreferences().getString("idx", "");
    }

    public static String getNickname() {
        return getSharedPreferences().getString("nickname", "");
    }

    //모든 저장정보 삭제.
    public static void clear() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }

    public static boolean isLogin() {
        return SaveSharedPreference.getID().length() != 0;
    }


}
