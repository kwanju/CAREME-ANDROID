package zangdol.careme.restapi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetMyInfo implements RestUtil.OnRestApiListener {

    private OnGetMyInfoListener listener;
    private String url = Config.SERVERIP + "android/user/json/getInfo";

    public interface OnGetMyInfoListener {
        void onGetMyInfo(HashMap<String, String> myinfo);
    }

    public GetMyInfo(OnGetMyInfoListener listener, final String idx) {
        this.listener = listener;
        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("user_idx", idx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetMyInfo(json2myinfo(result));
    }

    private HashMap<String, String> json2myinfo(JSONObject result) {
        HashMap<String, String> myinfo = new HashMap<>();

        try {
            JSONObject info = result.getJSONObject("info");
            myinfo.put("nickname", info.getString("nickname"));
            myinfo.put("phone_number", info.getString("phone_number"));
            myinfo.put("email", info.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myinfo;
    }

}
