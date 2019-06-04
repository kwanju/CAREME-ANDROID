package zangdol.careme.adoptionRecordList;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.AdoptionRecord;

public class AdoptionRecordListActivity extends AppCompatActivity
{
    ArrayList<AdoptionRecord> adoptionRecords;
    ListView adoptionRecordListView;
    private AdoptionRecordAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_record_list);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_adoption_list);

        adoptionRecordListView=(ListView)findViewById(R.id.adopt_list);
        adoptionRecords = new ArrayList<>();
        adapter= new AdoptionRecordAdapter( adoptionRecords, getApplicationContext());

////////////////// 사용자 아이디를 서버에 보내면 서버에서 유기견 입양신청 내역을 받아온다. 여기서는 일단 임의로 박아넣는다.
        adoptionRecords.add(new AdoptionRecord("댕댕이", "웰시코기", "해피해피보호소",
                                                         "2019-06-01","신청승인",null));
        adoptionRecords.add(new AdoptionRecord("보리", "치와와", "해피해피보호소",
                "2019-06-02","거절",null));
        adoptionRecords.add(new AdoptionRecord("꽁꽁이", "불독", "해피해피보호소",
                "2019-06-03","신청접수",null));


        adoptionRecordListView.setAdapter(adapter);
        adoptionRecordListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AdoptionRecord adoptionRecord = adoptionRecords.get(position);
////////////////////////////adoptionRecord에 있는 필요한 정보를 추출해 해당 유기견정보화면으로 넘어간다(intent).///////////////////////////////////
            }
        });

    }
}