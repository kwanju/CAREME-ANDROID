package zangdol.careme.SearchShelter.shelterListNew;

import android.app.Activity;

public class ShelterListNewContract {
    public interface View {
        Activity getActivity();
    }

    public interface Presenter {
        ShelterListNewAdapter getAdapter();
        void moveShelter(int position);
    }
}
