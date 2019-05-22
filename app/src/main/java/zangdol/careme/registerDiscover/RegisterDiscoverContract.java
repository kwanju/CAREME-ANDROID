package zangdol.careme.registerDiscover;

import android.app.Activity;
import android.net.Uri;

public class RegisterDiscoverContract {
    public interface View {
        Activity getActivity();
    }

    public interface Presenter {
        void setData(String key, String value);

        void register(RegisterDiscoverActivity.RegisterType registerType);

        void setImageUri(Uri uri);
    }
}
