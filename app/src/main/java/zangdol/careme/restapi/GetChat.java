package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetChat implements RestUtil.OnRestApiListener {

    private OnGetChatListener listener;
    private String url = Config.SERVERIP + "android/chat/json/getChat";

    public interface OnGetChatListener {
        void onGetChat(ArrayList<HashMap<String, String>> chats);
    }

    public GetChat(OnGetChatListener listener, final HashMap<String, String> data) {
        this.listener = listener;

        RestFactory.getInstance().request(url, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 2;
            }

            @Override
            public void setParams() {
                addParam("user_idx", data.get("user_idx"));
                addParam("shelter_idx", data.get("shelter_idx"));
            }
        });


    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onGetChat(json2chats(result));
    }

    private ArrayList<HashMap<String, String>> json2chats(JSONObject result) {
        ArrayList<HashMap<String, String>> chats = new ArrayList<>();

        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject chatJSON = list.getJSONObject(i);
                HashMap<String, String> chat = new HashMap<>();

                chat.put("type", chatJSON.getString("type"));
                chat.put("message", chatJSON.getString("message"));
                chat.put("send_time", chatJSON.getString("send_time"));

                chats.add(chat);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chats;
    }
}
