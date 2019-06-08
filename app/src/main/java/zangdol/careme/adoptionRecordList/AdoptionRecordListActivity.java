package zangdol.careme.adoptionRecordList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.AdoptionRecord;

public class AdoptionRecordListActivity extends AppCompatActivity implements AdoptionRecordContract.View {
    private ListView adoptionRecordListView;
    private AdoptionRecordContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_record_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_adoption_list);

        adoptionRecordListView = (ListView) findViewById(R.id.adopt_list);

        presenter = new AdoptionRecordPresenter(this);
        presenter.getData();
    }

    @Override
    public void setAdapter(final AdoptionRecordAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adoptionRecordListView.setAdapter(adapter);
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}