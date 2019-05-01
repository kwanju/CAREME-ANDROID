package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import zangdol.careme.Config;

public class Login implements RestUtil.OnRestApiListener {
    private OnLoginListener listener;
    private RestUtil restUtil;

    @Override
    public void OnResult(JSONObject result) {
        listener.onLogin(restUtil.json2map(result)); // 결과를 받았으니 subscriber에게 알려줌.
    }

    public interface OnLoginListener {
        void onLogin(HashMap<String, String> results);
    }


    public void login(String id, String pw, OnLoginListener listener) {
        this.listener = listener;
        restUtil = new RestUtil();

        String URL = Config.SERVERIP + "android/user/action/login"; // 로그인 POST URL

        String input_id = id;
        String input_pw = pw;

        //parameter
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);

        nameValuePairs.add(new BasicNameValuePair("id", input_id));
        nameValuePairs.add(new BasicNameValuePair("pw", input_pw));

        //요청 보냄.
        restUtil.request(URL, nameValuePairs, this);
    }


}
