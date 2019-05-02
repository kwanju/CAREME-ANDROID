package zangdol.careme.util.FCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import zangdol.careme.util.SaveSharedPreference;

public class FirebaseInstanceService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("IDService", "Refreshed token: " + refreshedToken);

        saveToken(refreshedToken);
    }

    private void saveToken(String token) {
        SaveSharedPreference.setToken(token);
    }

}
