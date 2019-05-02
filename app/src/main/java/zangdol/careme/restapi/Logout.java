package zangdol.careme.restapi;

import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class Logout implements RestUtil.OnRestApiListener {

    private OnLogoutListener listener;
    private final String URL = Config.SERVERIP + "android/user/action/logout";

    public interface OnLogoutListener {
        void onLogout();
    }

    public Logout(final String idx, OnLogoutListener listener) {
        this.listener = listener;

        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("idx", idx);
            }
        });

    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onLogout();
    }
}
