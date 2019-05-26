package zangdol.careme.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.FavoriteDog;
import zangdol.careme.model.FavoriteShelter;

public class MyPageActivity extends AppCompatActivity
{
    private FrameLayout fl;
    private ImageView profile_image;
    private TextView profile_nickname;
    private TextView profile_email;
    private TextView profile_phone;
    private Button bt_profile_edit;
    private ImageView profile_background_image;

    private TextView intro_myself;

    private ListView lv_favorite_dog;
    private ArrayList<FavoriteDog> favoriteDogs;
    private FavoriteDogAdapter adapter_dog;

    private ListView lv_favorite_shelter;
    private ArrayList<FavoriteShelter> favoriteShelters;
    private FavoriteShelterAdapter adapter_shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        setItem();
////////////////////////////서버에서 해당 사용자의 즐겨찾기_유기견 정보 목록을 불러와서 add해야 하는데 여기선 그냥 임의로 넣겠다.//////////////////////
        favoriteDogs.add( new FavoriteDog("말티즈", "꽁꽁이", "해피해피보호소", "서울 종로구",
                null));
        favoriteDogs.add( new FavoriteDog("말티즈", "꽁꽁이", "꽁공이네", "경기도 안산시",
                null));
        favoriteDogs.add( new FavoriteDog("말티즈", "꽁꽁이", "꽁공이네", "경기도 안산시",
                null));
////////////////////////////서버에서 해당 사용자의 즐겨찾기_보호소 정보 목록을 불러와서 add해야 하는데 여기선 그냥 임의로 넣겠다.//////////////////////
        favoriteShelters.add( new FavoriteShelter( "보호소1", "xxx-xxx-xxxx", "경기도 수원시",
                null ));
        favoriteShelters.add( new FavoriteShelter( "보호소1", "xxx-xxx-xxxx", "경기도 수원시",
                null ));
        favoriteShelters.add( new FavoriteShelter( "보호소1", "xxx-xxx-xxxx", "경기도 수원시",
                null ));
        favoriteShelters.add( new FavoriteShelter( "보호소1", "xxx-xxx-xxxx", "경기도 수원시",
                null ));

    }
    public void setItem()
    {
        fl = (FrameLayout)findViewById( R.id.framelayout_profile );
        profile_image = (ImageView)findViewById(R.id.profileImg);
        fl.bringChildToFront(profile_image);
        profile_nickname = (TextView)findViewById(R.id.tv_profile_nickname);
        fl.bringChildToFront(profile_nickname);
        profile_email = (TextView)findViewById(R.id.tv_profile_email);
        fl.bringChildToFront(profile_email);
        profile_phone = (TextView)findViewById(R.id.tv_profile_phone);
        fl.bringChildToFront(profile_phone);
        bt_profile_edit = (Button)findViewById(R.id.bt_profile_edit);
        profile_background_image = (ImageView)findViewById(R.id.profile_background);

        intro_myself = (TextView)findViewById(R.id.intro_myself);

        lv_favorite_dog = (ListView)findViewById(R.id.lv_favorite_dog);
        favoriteDogs = new ArrayList<>();
        adapter_dog = new FavoriteDogAdapter( favoriteDogs, getApplicationContext() );
        lv_favorite_dog.setAdapter(adapter_dog);
        lv_favorite_dog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FavoriteDog favoriteDog = favoriteDogs.get(position);
////////////////////////////favoriteDog에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
///////////////////////////해당견의 사이트로 연결한다. intent써서...//////////////////////////////////////
            }
        });

        lv_favorite_shelter = (ListView)findViewById(R.id.lv_favorite_shelter);
        favoriteShelters = new ArrayList<>();
        adapter_shelter = new FavoriteShelterAdapter( favoriteShelters, getApplicationContext() );
        lv_favorite_shelter.setAdapter(adapter_shelter);
        lv_favorite_shelter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FavoriteShelter favoriteShelter = favoriteShelters.get(position);
////////////////////////////favoriteShelter에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
///////////////////////////해당보호소의 사이트로 연결한다. intent써서...//////////////////////////////////////
            }
        });
    }


    public void onClick_profileEdit( View view )
    {
        startActivity(new Intent(this, ProfileEditActivity.class));
    }


}