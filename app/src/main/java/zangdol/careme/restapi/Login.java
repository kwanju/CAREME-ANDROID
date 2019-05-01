package zangdol.careme.restapi;

import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class Login implements RestUtil.OnRestApiListener {
    private OnLoginListener listener;

    @Override
    public void OnResult(JSONObject result) {
        listener.onLogin(RestUtil.json2map(result)); // 결과를 받았으니 subscriber에게 알려줌.
    }

    public interface OnLoginListener {
        void onLogin(HashMap<String, String> results);
    }


    public void login(String id, String pw, OnLoginListener listener) {
        this.listener = listener;

        String URL = Config.SERVERIP + "android/user/action/login"; // 로그인 POST URL

        final String input_id = id;
        final String input_pw = pw;


        RestFactory restFactory = RestFactory.getInstance();

        restFactory.request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 2;
            }

            @Override
            public void setParams() {
                addParam("id", input_id);
                addParam("pw", input_pw);
            }
        });
    }


}
