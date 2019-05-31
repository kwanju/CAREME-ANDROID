package zangdol.careme.discoverFindRecord;

import android.app.Activity;

public class DiscoverFindRecordContract {
    public interface View {
        Activity getActivity();
        void setAdapter(DiscoverFindRecordAdapter adapter);
    }

    public interface Presenter {
        void getDiscoverRecord();
    }
}
