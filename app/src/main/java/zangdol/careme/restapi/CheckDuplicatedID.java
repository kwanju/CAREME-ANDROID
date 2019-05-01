package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;

public class CheckDuplicatedID implements RestUtil.OnRestApiListener {

    private OnCheckDupIDListener listener;

    public interface OnCheckDupIDListener {
        void OnCheckDupID(boolean result);
    }

    public CheckDuplicatedID(String id, OnCheckDupIDListener listener) {
        this.listener = listener;
        RestUtil restUtil = new RestUtil();
        String url = Config.SERVERIP + "android/user/json/checkDupID";

        List<NameValuePair> params = new ArrayList<NameValuePair>(1);

        params.add(new BasicNameValuePair("id", id));

        restUtil.request(url, params, this);
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.OnCheckDupID(json2result(result));
    }

    private boolean json2result(JSONObject result) {
        try {
            if (result.getString("result").equals("1"))
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
