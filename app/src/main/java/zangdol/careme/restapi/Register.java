package zangdol.careme.restapi;

import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class Register implements RestUtil.OnRestApiListener {

    private OnRegisterListener listener;

    public Register(final String id, final String pw, final String nickname, final String pnum, final String email, final String token, OnRegisterListener listener) {
        this.listener = listener;


        String url = Config.SERVERIP + "android/user/action/registration";

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 6;
            }

            @Override
            public void setParams() {
                addParam("id", id);
                addParam("pw", pw);
                addParam("nickname", nickname);
                addParam("pnum", pnum);
                addParam("email", email);
                addParam("token",token);
            }
        });
    }

    public interface OnRegisterListener {
        void onResgiter(HashMap<String, String> results);
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onResgiter(RestUtil.json2map(result));
    }
}
