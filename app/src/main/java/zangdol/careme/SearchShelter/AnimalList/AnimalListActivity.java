package zangdol.careme.SearchShelter.AnimalList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import zangdol.careme.R;

public class AnimalListActivity extends AppCompatActivity implements AnimalListContract.View {

    private GridView gv_animalList;
    private AnimalListPresenter animalListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_animal_list);

        animalListPresenter = new AnimalListPresenter(this);

        setElement();

        animalListPresenter.setAnimalList( "1"); // ****나중에 activity에서 가져와야함.*****
    }

    private void setElement() {
        gv_animalList = (GridView) findViewById(R.id.gridview_animal_list);
    }

    @Override
    public void setGridAdapter(final AnimalListAdapter animalListAdapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gv_animalList.setAdapter(animalListAdapter);
            }
        });
    }
}
