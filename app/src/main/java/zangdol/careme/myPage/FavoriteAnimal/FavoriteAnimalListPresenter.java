package zangdol.careme.myPage.FavoriteAnimal;

import java.util.ArrayList;
import java.util.HashMap;

import zangdol.careme.model.GetFavoriteAnimalList;
import zangdol.careme.restapi.GetFavorites;

public class FavoriteAnimalListPresenter implements FavoriteAnimalListContract.Presenter,GetFavorites.OnGetFavoritesListener {
    private FavoriteAnimalListContract.View view;

    public FavoriteAnimalListPresenter(FavoriteAnimalListContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavoriteAnimalList() {
        ArrayList<String> favoriteIdxs = new GetFavoriteAnimalList(view.getActivity()).getFavoriteAnimals();
        new GetFavorites(this,favoriteIdxs);
    }

    @Override
    public void onGetFavorites(ArrayList<HashMap<String, String>> favorites) {
        view.setAdapter(new FavoriteAnimalAdapter(favorites,view.getActivity()));
    }
}
