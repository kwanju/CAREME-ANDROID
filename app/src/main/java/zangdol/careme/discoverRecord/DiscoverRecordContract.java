package zangdol.careme.discoverRecord;

import android.app.Activity;

public class DiscoverRecordContract {
    public interface View {
        Activity getActivity();
        void setAdapter(DiscoverRecordAdapter adapter);
    }

    public interface Presenter {
        void getDiscoverRecord();
    }
}
