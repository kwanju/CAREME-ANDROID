package zangdol.careme.discoverFindRecord;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import zangdol.careme.R;

public class DiscoverFindRecordActivity extends AppCompatActivity implements DiscoverFindRecordContract.View {

    private DiscoverFindRecordContract.Presenter presenter;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_record);
        presenter = new DiscoverFindRecordPresenter(this);
        setElements();
        presenter.getDiscoverRecord();
    }

    public void setElements() {
        listView = (ListView) findViewById(R.id.discover_record_listview);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setAdapter(final DiscoverFindRecordAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        });
    }
}
