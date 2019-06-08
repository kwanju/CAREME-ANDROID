package zangdol.careme.adoptionRecordList;

import android.app.Activity;

public class AdoptionRecordContract {
    public interface View{
        void setAdapter(AdoptionRecordAdapter adapter);
        Activity getActivity();
    }
    public interface Presenter{
        void getData();
    }
}
