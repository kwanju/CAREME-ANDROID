package zangdol.careme.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class AlarmManager {
    public static void alarm(String message, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("알림");
        alert.setMessage(message);
        alert.setPositiveButton("확인", null);
        alert.show();
    }

    public static void alarmYesNo(String message,Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("알림");
        alert.setMessage(message);
        alert.setPositiveButton("확인", listener);
        alert.setNegativeButton("취소", null);
        alert.show();
    }
}
