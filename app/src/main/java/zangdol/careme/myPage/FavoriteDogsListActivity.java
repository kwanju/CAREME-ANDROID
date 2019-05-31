package zangdol.careme.myPage;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.FavoriteDog;


public class FavoriteDogsListActivity extends AppCompatActivity {

    private ListView lv_favorite_dogs;
    private ArrayList<FavoriteDog> favoriteDogs;
    private FavoriteDogAdapter adapter_dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_dogs);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_text_alignment);
        // 액션바 타이틀 조정



        lv_favorite_dogs = (ListView)findViewById(R.id.lv_favorite_dogs);
        favoriteDogs = new ArrayList<>();
        adapter_dog = new FavoriteDogAdapter( favoriteDogs, getApplicationContext() );
        lv_favorite_dogs.setAdapter(adapter_dog);
        lv_favorite_dogs.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                FavoriteDog favoriteDog = favoriteDogs.get(position);
////////////////////////////favoriteDog에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
///////////////////////////해당견의 화면으로 연결한다. intent써서...//////////////////////////////////////
            }
        });


//////////////////////////////////서버에서 사용자의 관심유기견 등록한 리스트를 불러와서 박아 넣는다. 아래는 그냥 임의로 박아놓음./////////////////////////////////
        favoriteDogs.add( new FavoriteDog("말티즈", "꽁꽁이", "해피해피보호소", "서울 종로구 창경궁로 261",
                null));
        favoriteDogs.add( new FavoriteDog("도사견", "에삐", "수원시 유기견 보호 센터",
                "경기 수원시 영통구 영일로 26 1층",null));
        favoriteDogs.add( new FavoriteDog("불독", "아름누리", "꽁공이네", "경기도 안산시",
                null));
    }
}
