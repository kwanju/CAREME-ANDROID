package zangdol.careme.util.FCM;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;

import zangdol.careme.util.NotificationManager;


// https://web-inf.tistory.com/21
public class FCMService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG,remoteMessage.getData().toString());
            HashMap<String, String> data = new HashMap<>();
            data.put("mode", remoteMessage.getData().get("mode"));
            data.put("extradata", remoteMessage.getData().get("extradata"));
            sendNotification(data);
        }

    }

    private void sendNotification(HashMap<String, String> data) {
        NotificationManager.notification(this, data);
    }
}
