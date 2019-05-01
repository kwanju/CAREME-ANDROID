package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import zangdol.careme.Config;

public class Register implements RestUtil.OnRestApiListener {

    private OnRegisterListener listener;
    private RestUtil restUtil;
    public Register(String id,String pw,String nickname,String pnum, String email,OnRegisterListener listener) {
        this.listener = listener;
        restUtil = new RestUtil();
        String url = Config.SERVERIP+"android/user/action/registration";

        List<NameValuePair> params = new ArrayList<NameValuePair>(5);

        params.add(new BasicNameValuePair("id", id));
        params.add(new BasicNameValuePair("pw", pw));
        params.add(new BasicNameValuePair("nickname", nickname));
        params.add(new BasicNameValuePair("pnum", pnum));
        params.add(new BasicNameValuePair("email", email));

        restUtil.request(url,params,this);
    }

    public interface OnRegisterListener{
        void onResgiter(HashMap<String, String> results);
    }
    @Override
    public void OnResult(JSONObject result) {
        listener.onResgiter(restUtil.json2map(result));
    }
}
