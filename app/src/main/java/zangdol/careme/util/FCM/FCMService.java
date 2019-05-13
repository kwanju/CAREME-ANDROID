package zangdol.careme.util.FCM;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import zangdol.careme.util.NotificationManager;


// https://web-inf.tistory.com/21
public class FCMService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage.getData().get("body"));

        }

    }

    private void sendNotification(String messageBody) {
        NotificationManager.notification(this, "임시 제목",messageBody);
    }
}
