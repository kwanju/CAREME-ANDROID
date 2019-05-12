package zangdol.careme.map;

import android.app.Activity;

public class MapViewContract {
    public interface View {
        Activity getActivity();
    }

    public interface Presenter {
        void clickSetLocation(double[] location);
    }
}
