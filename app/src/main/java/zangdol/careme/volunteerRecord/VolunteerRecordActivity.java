package zangdol.careme.volunteerRecord;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import zangdol.careme.R;

public class VolunteerRecordActivity extends AppCompatActivity implements VolunteerRecordContract.View {
    private VolunteerRecordPresenter presenter;

    private ListView volunteerRecordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerrecord);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_volunteer_record);

        setElements();
        presenter = new VolunteerRecordPresenter(this);
        presenter.getSchedule();
    }

    private void setElements() {
        volunteerRecordListView = (ListView) findViewById(R.id.volunteerRecordList);
        
    }


    @Override
    public void setSchedule(final VolunteerRecordAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                volunteerRecordListView.setAdapter(adapter);
            }
        });
    }
}
