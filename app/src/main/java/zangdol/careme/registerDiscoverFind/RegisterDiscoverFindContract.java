package zangdol.careme.registerDiscoverFind;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class RegisterDiscoverFindContract {
    public interface View {
        Activity getActivity();
        Intent getIntent();
    }

    public interface Presenter {
        void setData(String key, String value);

        void register(RegisterDiscoverFindActivity.RegisterType registerType);

        void setImageUri(Uri uri);
    }
}
