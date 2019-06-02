package zangdol.careme.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class PermissionCheck {
    private AppCompatActivity activity;
    private String[] permissions = {
            Manifest.permission.INTERNET,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
    };
    public static final int MULTIPLE_PERMISSIONS = 101;


    public PermissionCheck(AppCompatActivity activity) {
        this.activity = activity;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public void checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23)
            checkPermission();
    }

    private void checkPermission() {
        int result;
        List<String> permissionList = new ArrayList<>();
        for (String pm : permissions) {
            result = ContextCompat.checkSelfPermission(activity, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), MULTIPLE_PERMISSIONS);
            return;
        }
        return;
    }

}
