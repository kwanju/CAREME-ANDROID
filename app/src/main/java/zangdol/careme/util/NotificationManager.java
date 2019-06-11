package zangdol.careme.util;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import zangdol.careme.R;
import zangdol.careme.adoptionRecordList.AdoptionRecordListActivity;
import zangdol.careme.animal.AnimalInfoActivity;
import zangdol.careme.bulletinBoardDiscoverFind.discoverFind.DiscoverFindActivity;
import zangdol.careme.chat.ChatActivity;
import zangdol.careme.main.MainActivity;
import zangdol.careme.volunteerRecord.VolunteerRecordActivity;

public class NotificationManager {

    public final static String CHAT = "0"; // mode 0 s
    public final static String DISCOVERFIND = "1";
    public final static String ANIMALFIND = "2";
    public final static String SCHEDULE = "3";
    public final static String DISCOVERMATHCING = "4";
    public final static String ADOPT = "5";

    public static void notification(Context context, HashMap<String, String> data) {
        Intent intent = setIntent(data, context);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(intent);

        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        String channelId = context.getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, channelId)
                        .setSmallIcon(R.drawable.app_mark)
                        .setColor(ContextCompat.getColor(context, R.color.colorAccent))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        setTitleText(notificationBuilder, data);
        android.app.NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = context.getString(R.string.default_notification_channel_name);
            NotificationChannel channel = new NotificationChannel(channelId, channelName, android.app.NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0, notificationBuilder.build());
    }

    private static Intent setIntent(HashMap<String, String> data, Context context) {
        String mode = data.get("mode");
        if (mode.equals(CHAT)) {
            Intent intent = new Intent(context, ChatActivity.class);
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                intent.putExtra("shelter_name", extraData.getString("shelter_name"));
                intent.putExtra("shelter_idx", extraData.getString("shelter_idx"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return intent;
        } else if (mode.equals(DISCOVERFIND)) {
            Intent intent = new Intent(context, DiscoverFindActivity.class);
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                intent.putExtra("idx", extraData.getString("idx"));
                intent.putExtra("code", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return intent;
        } else if (mode.equals(ANIMALFIND)) {
            Intent intent = new Intent(context, AnimalInfoActivity.class);
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                intent.putExtra("idx", Integer.parseInt(extraData.getString("idx")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return intent;
        } else if (mode.equals(SCHEDULE))
            return new Intent(context, VolunteerRecordActivity.class);
        else if (mode.equals(DISCOVERMATHCING)) {
            Intent intent = new Intent(context, DiscoverFindActivity.class);
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                intent.putExtra("idx", extraData.getString("discover_idx"));
                intent.putExtra("code", 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return intent;
        } else if (mode.equals(ADOPT)) {
            Intent intent = new Intent(context, AdoptionRecordListActivity.class);
            return intent;
        }

        return new Intent(context, MainActivity.class);
    }

    private static void setTitleText(NotificationCompat.Builder builder, HashMap<String, String> data) {
        String mode = data.get("mode");
        if (mode.equals(CHAT)) {
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                builder.setContentTitle(extraData.getString("shelter_name"))
                        .setContentText(extraData.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        } else if (mode.equals(DISCOVERFIND) || mode.equals(ANIMALFIND)) {
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                builder.setContentTitle("찾아요")
                        .setContentText(extraData.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        } else if (mode.equals(SCHEDULE)) {
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                builder.setContentTitle("봉사신청")
                        .setContentText(extraData.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        } else if (mode.equals(DISCOVERMATHCING)) {
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                builder.setContentTitle("발견했어요(보호소매칭)")
                        .setContentText(extraData.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        } else if(mode.equals(ADOPT)){
            try {
                JSONObject extraData = new JSONObject(data.get("extradata"));
                builder.setContentTitle("입양")
                        .setContentText(extraData.getString("message"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }

        builder.setContentTitle("CAREME")
                .setContentText("알람이 왔습니다.");
    }
}
