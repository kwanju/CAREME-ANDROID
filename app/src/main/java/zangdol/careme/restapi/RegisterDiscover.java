package zangdol.careme.restapi;

import android.content.Context;
import android.net.Uri;

import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.ImageParameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class RegisterDiscover implements RestUtil.OnRestApiListener {

    private OnRegisterDiscoverListener listener;

    private final static String URL = Config.SERVERIP + "android/discover/action/registerDiscover";

    public interface OnRegisterDiscoverListener {
        void OnRegisterDiscover();
    }

    public RegisterDiscover(OnRegisterDiscoverListener listener, final HashMap<String, String> data, final Uri uri, Context context) {
        this.listener = listener;

        RestFactory.getInstance().uploadImage(URL, this, new ImageParameters(context) {
            @Override
            public void addParams() {
                addTextParam("discover_datetime", data.get("datetime"));
                addTextParam("discover_spot", data.get("address"));
                addTextParam("description", data.get("description"));
                addTextParam("species_code", data.get("species_code"));
                addTextParam("animal_sex", data.get("sex"));
                addTextParam("user_idx", data.get("user_idx")); // 이 부분은 따로 세팅해야함.

                if (uri != null)
                    addImageParam("animalImage", uri);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.OnRegisterDiscover();
    }
}
