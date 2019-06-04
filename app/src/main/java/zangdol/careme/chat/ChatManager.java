package zangdol.careme.chat;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import zangdol.careme.Config;

public class ChatManager {
    private Socket mSocket;

    public void connect(String idx) {
        try {
            mSocket = IO.socket(Config.SERVERIP);
            mSocket.connect();
            JSONObject data = new JSONObject();
            data.put("type", "user");
            data.put("idx", idx);
            mSocket.emit("participate", data.toString());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        mSocket.close();
    }

    public void sendMessage(HashMap<String,String> data) {
        mSocket.emit("message",makeData(data));
    }

    private String makeData(HashMap<String, String> data) {
        JSONObject dataJSON = new JSONObject();

        for (String key : data.keySet()) {
            try {
                dataJSON.put(key, data.get(key));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return dataJSON.toString();
    }
}
