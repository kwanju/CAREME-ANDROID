package zangdol.careme.restapi;

import android.content.Context;
import android.net.Uri;

import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.ImageParameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class RegisterFind implements RestUtil.OnRestApiListener{

    private OnRegisterFindListener listener;

    private final static String URL = Config.SERVERIP + "android/find/action/registerFind";

    public interface OnRegisterFindListener {
        void OnRegisterFind();
    }

    public RegisterFind(OnRegisterFindListener listener, final HashMap<String, String> data, final Uri uri, Context context) {
        this.listener = listener;

        RestFactory.getInstance().uploadImage(URL, this, new ImageParameters(context) {
            @Override
            public void addParams() {
                addTextParam("lost_datetime", data.get("datetime"));
                addTextParam("lost_spot", data.get("address"));
                addTextParam("description", data.get("description"));
                addTextParam("species_code", data.get("species_code"));
                addTextParam("animal_sex", data.get("sex"));
                addTextParam("user_idx", data.get("user_idx")); // 이 부분은 따로 세팅해야함.
                addTextParam("latitude", data.get("latitude"));
                addTextParam("longitude",data.get("longitude"));

                if (uri != null)
                    addImageParam("animalImage", uri);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.OnRegisterFind();
    }
}
