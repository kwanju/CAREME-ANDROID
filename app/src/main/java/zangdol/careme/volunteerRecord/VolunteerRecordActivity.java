package zangdol.careme.volunteerRecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.VolunteerRecord;

public class VolunteerRecordActivity extends AppCompatActivity
{
    ArrayList<VolunteerRecord> volunteerRecords;
    ListView volunteerRecordListView;
    private static VolunteerRecordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerrecord);

        volunteerRecordListView=(ListView)findViewById(R.id.volunteerRecordList);
        volunteerRecords = new ArrayList<>();
        adapter= new VolunteerRecordAdapter( volunteerRecords,getApplicationContext());

        //////// 사용자의 아이디를 서버에 보내면 서버에서 봉사활동 내역을 받아온다.
        volunteerRecords.add(new VolunteerRecord("유기견보호소1", "댕댕이", "May 7, 2019"));
        volunteerRecords.add(new VolunteerRecord("유기견보호소2", "멍멍이", "May 17, 2019"));

        volunteerRecordListView.setAdapter(adapter);
        volunteerRecordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VolunteerRecord volunteerRecord = volunteerRecords.get(position);


            }
        });
    }
}
