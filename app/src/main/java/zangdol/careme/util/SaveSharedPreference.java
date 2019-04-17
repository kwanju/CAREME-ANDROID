package zangdol.careme.util;

        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;

        import zangdol.careme.main.MainActivity;

public class SaveSharedPreference {

    static SharedPreferences getSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(MainActivity.getContextOfApplication());
    }

    public static void setUser(String id,String idx)
    {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString("id", id);
        editor.putString("idx",idx);
        editor.commit();

    }

    public static String getID()
    {
        return getSharedPreferences().getString("id", "");
    }

    public static String getIdx(){
        return getSharedPreferences().getString("idx", "");
    }

    public static void clear()
    {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.commit();
    }


}
