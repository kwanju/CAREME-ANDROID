package zangdol.careme.SearchShelter.shelterListNew;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import zangdol.careme.R;

public class ShelterListNewActivity extends AppCompatActivity implements ShelterListNewContract.View {
    private ListView shelterDataListView;
    private ShelterListNewContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterlist);

        presenter = new ShelterListNewPresenter(this);
        setElements();
        setAdapter();

        shelterDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.moveShelter(position);
            }
        });
    }

    private void setAdapter() {
        ShelterListNewAdapter adapter = presenter.getAdapter();
        if(adapter==null)
            Log.d("TEST","it is null");
        else
            shelterDataListView.setAdapter(presenter.getAdapter());
    }

    private void setElements() {
        shelterDataListView = (ListView) findViewById(R.id.shelterLV);
    }


    @Override
    public Activity getActivity() {
        return this;
    }


}
