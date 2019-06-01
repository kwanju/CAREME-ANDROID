package zangdol.careme.myPage.FavoriteAnimal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.FavoriteDog;


public class FavoriteAnimalListActivity extends AppCompatActivity implements FavoriteAnimalListContract.View {

    private ListView lv_favorite_dogs;
    private ArrayList<FavoriteDog> favoriteDogs;
    private FavoriteAnimalAdapter adapter_dog;

    private FavoriteAnimalListContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_dogs);
        lv_favorite_dogs = (ListView) findViewById(R.id.lv_favorite_dogs);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_text_alignment);
        // 액션바 타이틀 조정

        presenter = new FavoriteAnimalListPresenter(this);

        presenter.getFavoriteAnimalList();

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setAdapter(final FavoriteAnimalAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv_favorite_dogs.setAdapter(adapter);
            }
        });
    }
}
