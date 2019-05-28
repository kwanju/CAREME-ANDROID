package zangdol.careme.searchFilterDogs;

import android.app.Activity;

public class SearchFilterDogsContract {
    public interface View{
        void setAdapter(DogFilterAdapter adapter);
        Activity getActivity();
    }
    public interface Presenter{
        void search();
        void setData(String key, String value);
    }
}
