package zangdol.careme.SearchShelter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import zangdol.careme.R;

public class SearchShelterCategoryActivity extends AppCompatActivity {
    ListView lv1;
    ListView lv2;
    private ArrayList<String> location1 = new ArrayList<String>();
    private ArrayList<String> location2 = new ArrayList<String>();
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shelter_category);
        lv1 = (ListView) findViewById( R.id.lv1 );
        lv2 = (ListView) findViewById( R.id.lv2 );
        location1.add( "서울시" ); location1.add( "경기도" ); location1.add( "인천" );
        location1.add( "강원도" ); location1.add( "충청도" ); location1.add( "대전" );
        location1.add( "전라도" ); location1.add( "광주" );   location1.add( "경상도" );
        location1.add( "대구" );   location1.add( "울산" );   location1.add( "부산" );
        location1.add( "제주도" );


        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, location1);
        adapter2 = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, location2);
        lv1.setAdapter( adapter1 );
        lv2.setAdapter( adapter2 );

        lv1.setOnItemClickListener( new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if( position == 0 )
                {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add( "종로구" );location2.add( "중구" );location2.add( "용산구" );
                    location2.add( "성동구" );location2.add( "광진구" );location2.add( "동대문구" );
                    location2.add( "중랑구" );location2.add( "성북구" );location2.add( "강북구" );
                    location2.add( "도봉구" );location2.add( "노원구" );location2.add( "은평구" );
                    location2.add( "서대문구" );location2.add( "마포구" );location2.add( "양천구" );
                    location2.add( "강서구" );location2.add( "구로구" );location2.add( "금천구" );
                    location2.add( "영등포구" );location2.add( "동작구" );location2.add( "관악구" );
                    location2.add( "서초구" );location2.add( "강남구" );location2.add( "송파구" );
                    location2.add( "강동구" );

                }
                else if( position == 1)
                {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add( "수원" ); location2.add( "용인" ); location2.add( "화성" );
                    location2.add( "성남" ); location2.add( "의정부" ); location2.add( "안양" );
                    location2.add( "부천" ); location2.add( "광명" ); location2.add( "평택" );
                    location2.add( "동두천" ); location2.add( "안산" ); location2.add( "고양" );
                    location2.add( "과천" ); location2.add( "구리" ); location2.add( "남양주" );
                    location2.add( "시흥" ); location2.add( "군포" ); location2.add( "의왕" );
                    location2.add( "하남" ); location2.add( "파주" ); location2.add( "이천" );
                    location2.add( "안성" ); location2.add( "김포" ); location2.add( "광주" );
                    location2.add( "양주" ); location2.add( "포천" ); location2.add( "여주" );
                    location2.add( "연천군" ); location2.add( "가평군" ); location2.add( "양평군" );
                }
                else if( position == 2)
                {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add( "중구" ); location2.add( "동구" ); location2.add( "미추홀구" );
                    location2.add( "연수구" ); location2.add( "남동구" ); location2.add( "부평구" );
                    location2.add( "계양구" ); location2.add( "서구" ); location2.add( "강화군" );
                    location2.add( "옹진군" );
                }
                else if( position == 3)
                {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                    location2.add( "춘천시" ); location2.add( "원주시" ); location2.add( "강릉시" );
                    location2.add( "동해시" ); location2.add( "태백시" ); location2.add( "속초시" );
                    location2.add( "삼척시" ); location2.add( "홍천군" ); location2.add( "횡성군" );
                    location2.add( "영월군" ); location2.add( "평창군" ); location2.add( "정선군" );
                    location2.add( "철원군" ); location2.add( "화천군" ); location2.add( "양구군" );
                    location2.add( "인제군" ); location2.add( "고성군" ); location2.add( "양양군" );
                }
                else
                {
                    location2.clear();
                    adapter2.notifyDataSetChanged();
                }
            }
        });

        lv2.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(location2.get(position).equals( "수원" ))
                {
                    //////서버로 수원에 있는 보호소 정보 뜯어오기
                    //////없다면 토스로 "해당 지역에는 보호소가 없습니다." 띠워주기
                    Intent intent = new Intent( SearchShelterCategoryActivity.this, SearchShelterCategoryActivity.class );
                    startActivity(intent);
                }
            }
        });

    }
}
