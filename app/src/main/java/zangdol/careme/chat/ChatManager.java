package zangdol.careme.chat;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import zangdol.careme.Config;

public class ChatManager {
    private Socket mSocket;
    private OnChatListener listener;

    public interface OnChatListener {
        void onMessage(HashMap<String,String> message);
    }

    public void connect(String idx, OnChatListener listener) {
        this.listener = listener;
        try {
            mSocket = IO.socket(Config.SERVERIP);
            mSocket.connect();
            setEvent();
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

    public void sendMessage(HashMap<String, String> data) {
        mSocket.emit("message", makeData(data));
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

    private void setEvent() {
        mSocket.on("message", onMessageListener);
    }

    private Emitter.Listener onMessageListener = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            try {
                JSONObject receivedData = new JSONObject(args[0].toString());
                Log.d("Test",receivedData.toString());
                HashMap<String,String> message =new HashMap<>();
                message.put("message",receivedData.getString("message"));
                message.put("send_time",receivedData.getString("send_time"));
                listener.onMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
