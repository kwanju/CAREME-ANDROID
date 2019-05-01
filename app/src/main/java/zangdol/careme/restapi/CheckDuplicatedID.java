package zangdol.careme.restapi;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class CheckDuplicatedID implements RestUtil.OnRestApiListener {

    private OnCheckDupIDListener listener;

    public interface OnCheckDupIDListener {
        void OnCheckDupID(boolean result);
    }

    public CheckDuplicatedID(final String id, OnCheckDupIDListener listener) {
        this.listener = listener;

        String url = Config.SERVERIP + "android/user/json/checkDupID";

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("id", id);
            }
        });
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
