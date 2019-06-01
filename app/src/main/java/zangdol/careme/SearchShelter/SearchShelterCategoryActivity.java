package zangdol.careme.SearchShelter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;

public class SearchShelterCategoryActivity extends AppCompatActivity implements SearchShelterCategoryContract.View {

    private SearchShelterCategoryPresenter presenter;
    ListView lv1;
    ListView lv2;
    private ArrayList<String> location1 = new ArrayList<String>();
    private ArrayList<String> location2 = new ArrayList<String>();
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    private String bigLocation;
    private String smallLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shelter_category);

        presenter = new SearchShelterCategoryPresenter(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_locations_category);

        setElements();
    }

    private void setElements() {
        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        location1.add("서울");
        location1.add("경기");
        location1.add("인천");
        location1.add("강원");
        location1.add("충북");
        location1.add("충남");
        location1.add("대전");
        location1.add("전북");
        location1.add("전남");
        location1.add("광주");
        location1.add("경북");
        location1.add("경남");
        location1.add("대구");
        location1.add("울산");
        location1.add("부산");
        location1.add("제주특별자치도");


        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, location1);
        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, location2);
        lv1.setAdapter(adapter1);
        lv2.setAdapter(adapter2);

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bigLocation = location1.get(position);
                smallLocation = null;

                if (position == 0) {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add("종로구");
                    location2.add("중구");
                    location2.add("용산구");
                    location2.add("성동구");
                    location2.add("광진구");
                    location2.add("동대문구");
                    location2.add("중랑구");
                    location2.add("성북구");
                    location2.add("강북구");
                    location2.add("도봉구");
                    location2.add("노원구");
                    location2.add("은평구");
                    location2.add("서대문구");
                    location2.add("마포구");
                    location2.add("양천구");
                    location2.add("강서구");
                    location2.add("구로구");
                    location2.add("금천구");
                    location2.add("영등포구");
                    location2.add("동작구");
                    location2.add("관악구");
                    location2.add("서초구");
                    location2.add("강남구");
                    location2.add("송파구");
                    location2.add("강동구");

                } else if (position == 1) {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add("수원시");
                    location2.add("용인시");
                    location2.add("화성시");
                    location2.add("성남시");
                    location2.add("의정부시");
                    location2.add("안양시");
                    location2.add("부천시");
                    location2.add("광명시");
                    location2.add("평택시");
                    location2.add("동두천시");
                    location2.add("안산시");
                    location2.add("고양시");
                    location2.add("과천시");
                    location2.add("구리시");
                    location2.add("남양주시");
                    location2.add("시흥시");
                    location2.add("군포시");
                    location2.add("의왕시");
                    location2.add("하남시");
                    location2.add("파주시");
                    location2.add("이천시");
                    location2.add("안성시");
                    location2.add("김포시");
                    location2.add("광주시");
                    location2.add("양주시");
                    location2.add("포천시");
                    location2.add("여주시");
                    location2.add("연천군");
                    location2.add("가평군");
                    location2.add("양평군");
                } else if (position == 2) {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add("중구");
                    location2.add("동구");
                    location2.add("미추홀구");
                    location2.add("연수구");
                    location2.add("남동구");
                    location2.add("부평구");
                    location2.add("계양구");
                    location2.add("서구");
                    location2.add("강화군");
                    location2.add("옹진군");
                } else if (position == 3) {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add("춘천시");
                    location2.add("원주시");
                    location2.add("강릉시");
                    location2.add("동해시");
                    location2.add("태백시");
                    location2.add("속초시");
                    location2.add("삼척시");
                    location2.add("홍천군");
                    location2.add("횡성군");
                    location2.add("영월군");
                    location2.add("평창군");
                    location2.add("정선군");
                    location2.add("철원군");
                    location2.add("화천군");
                    location2.add("양구군");
                    location2.add("인제군");
                    location2.add("고성군");
                    location2.add("양양군");
                } else if (position == 4) {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add("청주시");
                    location2.add("충주시");
                    location2.add("제천시");
                    location2.add("보은군");
                    location2.add("옥천군");
                    location2.add("영동군");
                    location2.add("증평군");
                    location2.add("진천군");
                    location2.add("괴산군");
                    location2.add("음성군");
                    location2.add("단양군");
                } else {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                smallLocation = location2.get(position);
                /////////////////////String bigLocation에 시/도 단위 지역명;////////////////////////////////////////////////////////////
                //String smallLocation에 시/군/구 단위 지역명;
                //아래는 토스트로 bigLocation과 smallLocation이 정확히 입력됬는지 알 수 있게 하기 위한 코드
                Toast.makeText(getApplicationContext(), bigLocation + " " + smallLocation, Toast.LENGTH_LONG).show();

                presenter.searchShelter(bigLocation,smallLocation);

//////////////////////////서버로 해당 지역(bigLocation smallLocation)에 보호소가 있는지 뜯어오기//////////////////////////
//////////////////////////없다면 토스트로 "해당 지역에는 보호소가 없습니다." 띠워주기/////////////////////////////////////
//////////////////////있으면 해당 지역을 intent에 담기////////////////////////////////////////////////////////////////
                // 아래는 intent를 이용해 다음 화면으로 넘어가는 코드

            }

        });
    }


}
