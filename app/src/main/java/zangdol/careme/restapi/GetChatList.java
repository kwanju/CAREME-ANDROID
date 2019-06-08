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

public class GetChatList implements RestUtil.OnRestApiListener {

    private OnGetChatListListener listener;
    private String url = Config.SERVERIP + "android/chat/json/getChatList";

    public interface OnGetChatListListener {
        void onGetChatList(ArrayList<HashMap<String, String>> chats);
    }

    public GetChatList(OnGetChatListListener listener, final String idx) {
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
        listener.onGetChatList(json2chats(result));
    }

    private ArrayList<HashMap<String, String>> json2chats(JSONObject result) {
        ArrayList<HashMap<String, String>> chats = new ArrayList<>();
        try {
            JSONArray list = result.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject chatJson = list.getJSONObject(i);
                HashMap<String, String> chat = new HashMap<>();
                chat.put("shelterName", chatJson.getString("shelterName"));
                chat.put("shelter_idx", chatJson.getString("shelter_idx"));
                chat.put("message", chatJson.getString("message"));
                chat.put("recent_send_time", chatJson.getString("recent_send_time"));
                chat.put("count", chatJson.getString("count"));
                chats.add(chat);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return chats;
    }
}
