package zangdol.careme.myPage.myPage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import zangdol.careme.R;
import zangdol.careme.adoptionRecordList.AdoptionRecordListActivity;
import zangdol.careme.discoverFindRecord.DiscoverFindRecordActivity;
import zangdol.careme.myPage.FavoriteAnimal.FavoriteAnimalListActivity;
import zangdol.careme.myPage.ProfileEditActivity;
import zangdol.careme.volunteerRecord.VolunteerRecordActivity;

public class MyPageActivity extends AppCompatActivity implements MyPageContract.View, View.OnClickListener {
    private MyPageContract.Presenter presenter;
    private FrameLayout fl;
    private TextView profile_nickname;
    private TextView profile_email;
    private TextView profile_phone;
    private Button bt_profile_edit;
    private ImageView profile_background_image;

    private ImageView tv_favorite_dog; // 클릭시 관심동물 리스트 화면으로 넘어가는 이미지뷰
    private ImageView volunteer_status; // 클릭시 봉사활동 목록 화면으로 넘어가는 이미지뷰
    private ImageView lost_found_mylist; // 클릭시 나의 찾아요/발견했어요 목록 화면으로 넘어가는 이미지뷰
    private ImageView adoption_record; // 클릭시 나의 입양신청기록 목록 화면으로 넘어가는 이미지뷰

    private TextView intro_myself;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        presenter = new MyPagePresenter(this);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_mypage);

        setItem();

        presenter.getData();
    }

    public void setItem() {
        fl = (FrameLayout) findViewById(R.id.framelayout_profile);
        profile_nickname = (TextView) findViewById(R.id.tv_profile_nickname);
        fl.bringChildToFront(profile_nickname);
        profile_email = (TextView) findViewById(R.id.tv_profile_email);
        fl.bringChildToFront(profile_email);
        profile_phone = (TextView) findViewById(R.id.tv_profile_phone);
        fl.bringChildToFront(profile_phone);
        bt_profile_edit = (Button) findViewById(R.id.bt_profile_edit);
        profile_background_image = (ImageView) findViewById(R.id.profile_background);

        intro_myself = (TextView) findViewById(R.id.intro_myself);
//////////////////////////////소개글 박아넣는다//////////////////////////////////

        tv_favorite_dog = (ImageView) findViewById(R.id.favorite_dog);
        volunteer_status = (ImageView) findViewById(R.id.volunteer_status);
        lost_found_mylist = (ImageView) findViewById(R.id.lost_found_mylist);
        adoption_record = (ImageView) findViewById(R.id.adoption_record);

        tv_favorite_dog.setOnClickListener(this);
        bt_profile_edit.setOnClickListener(this);
        volunteer_status.setOnClickListener(this);
        lost_found_mylist.setOnClickListener(this);
        adoption_record.setOnClickListener(this);
    }


    @Override
    public void setData(final HashMap<String, String> myinfo) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                profile_email.setText(myinfo.get("email"));
                profile_nickname.setText(myinfo.get("nickname"));
                profile_phone.setText(myinfo.get("phone_number"));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_profile_edit:
                startActivity(new Intent(MyPageActivity.this, ProfileEditActivity.class));
                break;
            case R.id.favorite_dog:
                startActivity(new Intent(MyPageActivity.this, FavoriteAnimalListActivity.class));
                break;
            case R.id.volunteer_status:
                startActivity(new Intent(this,VolunteerRecordActivity.class));
                break;
            case R.id.lost_found_mylist:
                startActivity(new Intent(this,DiscoverFindRecordActivity.class));
                break;
            case R.id.adoption_record:
                startActivity(new Intent(this, AdoptionRecordListActivity.class));
                break;
        }
    }
}