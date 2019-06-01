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

    private TextView tv_favorite_dog; // 클릭시 관심동물 리스트 화면으로 넘어가는 텍스트뷰
    private TextView volunteer_status; // 클릭시 봉사활동 목록 화면으로 넘어가는 텍스트뷰
    private TextView lost_found_mylist; // 클릭시 나의 찾아요/발견했어요 목록 화면으로 넘어가는 텍스트뷰

    private TextView intro_myself;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        setItem();

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
//////////////////////////////소개글 박아넣는다//////////////////////////////////

        tv_favorite_dog = (TextView)findViewById(R.id.favorite_dog);
        volunteer_status = (TextView)findViewById(R.id.volunteer_status);
        lost_found_mylist = (TextView)findViewById(R.id.lost_found_mylist);

        tv_favorite_dog.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyPageActivity.this, FavoriteDogsListActivity.class));
            }
        });

        bt_profile_edit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent( MyPageActivity.this, ProfileEditActivity.class));
            }
        });
        volunteer_status.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //startActivity(new Intent( MyPageActivity.this, .class));// 봉사 목록 화면으로 넘어간다.
            }
        });
        lost_found_mylist.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //startActivity(new Intent( MyPageActivity.this, .class));// 나의 찾아요/발견했어요 목록 화면으로 넘어간다.
            }
        });
    }


}