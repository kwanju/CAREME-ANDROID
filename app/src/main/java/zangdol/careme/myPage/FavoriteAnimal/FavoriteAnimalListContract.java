package zangdol.careme.myPage.FavoriteAnimal;

import android.app.Activity;

public class FavoriteAnimalListContract {
    public interface View{
        Activity getActivity();
        void setAdapter(FavoriteAnimalAdapter adapter);
    }
    public interface Presenter{
        void getFavoriteAnimalList();
    }
}
