package zangdol.careme.shelter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.ShelterData;

public class ShelterListActivity extends AppCompatActivity
{
    ArrayList<ShelterData> shelterDatas;
    ListView shelterDataListView;
    private ShelterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterlist);

        shelterDataListView=(ListView)findViewById(R.id.shelterDataList);
        shelterDatas = new ArrayList<>();
        adapter= new ShelterListAdapter( shelterDatas,getApplicationContext());

        //////// 사용자의 아이디를 서버에 보내면 서버에서 봉사활동 내역을 받아온다.
        shelterDatas.add(new ShelterData("유기견보호소1", "서울특별시 종로구 창성동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소2", "서울특별시 종로구 청운동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소3", "서울특별시 종로구 신교동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소4", "서울특별시 종로구 궁정동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소5", "서울특별시 종로구 효자동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소6", "서울특별시 종로구 통의동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소7", "서울특별시 종로구 적선동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소8", "서울특별시 종로구 옥인동", "xx-xxx-xxxx"));
        shelterDatas.add(new ShelterData("유기견보호소9", "서울특별시 종로구 내자동", "xx-xxx-xxxx"));



        shelterDataListView.setAdapter(adapter);
        shelterDataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ShelterData shelterData = shelterDatas.get(position);
/////////////////////
                 Toast.makeText( getApplicationContext(), shelterData.getShelterName()
                         + " " +shelterData.getAddress() + " "+ shelterData.getPhone(), Toast.LENGTH_LONG).show();

            }
        });
    }
}
