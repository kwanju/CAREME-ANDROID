package zangdol.careme.newMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import zangdol.careme.R;
import zangdol.careme.SearchShelter.SearchShelterCategoryActivity;
import zangdol.careme.bulletinBoardDiscoverFind.BulletinBoardDiscoverFindActivity;
import zangdol.careme.chat.ChatRoomListActivity;
import zangdol.careme.login.LoginActivity;
import zangdol.careme.model.ItemForMain;
import zangdol.careme.myPage.myPage.MyPageActivity;
import zangdol.careme.searchFilterDogs.SearchFilterDogsActivity;
import zangdol.careme.util.SaveSharedPreference;

public class NewMainActivity extends AppCompatActivity implements NewMainContract.View, View.OnClickListener {
    public static Context contextOfApplication; // 공통의 정보를 저장하기 위해서.

    private Button bt_login;
    private Button bt_mypage;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private ConstraintLayout cl_inLogin;
    private TextView tv_nickname;
    private Button btn_logout;

    private NewMainContract.Presenter presenter;
    GridView gridView;
    ArrayList<ItemForMain> gridArray = new ArrayList<ItemForMain>();
    MainGridViewAdapter mainGridAdapter;
    //그리드뷰에 이용될 것들...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternativemain);

        setItem();
        contextOfApplication = getApplicationContext();

        presenter = new NewMainPresenter(this);
        presenter.checkLogin();
        presenter.getSpeciesCode();


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //네비게이션 뷰에서 아이템 클릭 객체 생성
        //    navigationView.setNavigationItemSelectedListener(this);

        Bitmap shelterIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.shelter_main);
        Bitmap dogIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.dog_main);
        Bitmap lost_found_Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.lost_found_main);
        Bitmap chatroom_icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.chatroom_main);
//////////////////////각각의 기능의 아이콘을 만들어 아래 gridArray에 기능명과 함께 add한다. /////////////////////
        gridArray.add(new ItemForMain(shelterIcon, "보호소찾기"));
        gridArray.add(new ItemForMain(lost_found_Icon, "발견했어요/찾아요"));
        gridArray.add(new ItemForMain(dogIcon, "유기동물 검색"));
        gridArray.add(new ItemForMain(chatroom_icon, "보호소 채팅방"));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ItemForMain itemForMain = gridArray.get(position);
                Toast.makeText(NewMainActivity.this, "index" + position + "" + itemForMain.getFunctionName(), Toast.LENGTH_SHORT).show();
                if (position == 0)
                    startActivity(new Intent(NewMainActivity.this, SearchShelterCategoryActivity.class));
                else if (position == 1)
                    startActivity(new Intent(NewMainActivity.this, BulletinBoardDiscoverFindActivity.class));
                else if (position == 2)
                    startActivity(new Intent(NewMainActivity.this, SearchFilterDogsActivity.class));
                else if (position == 3)
                    startActivity(new Intent(NewMainActivity.this, ChatRoomListActivity.class));
            }
        });

    }

    public void setItem() {
        bt_login = (Button) findViewById(R.id.login_button);
        bt_mypage = (Button) findViewById(R.id.bt_mypage);

        cl_inLogin = (ConstraintLayout) findViewById(R.id.aam_cl_in_login);
        tv_nickname = (TextView) findViewById(R.id.aam_tv_nickname);
        btn_logout = (Button) findViewById(R.id.aam_btn_logout);

        navigationView = findViewById(R.id.navigation_view);
        // 드로어 토글 버튼 생성
        drawerLayout = findViewById(R.id.drawerLayout);

        ////////////////////////////이건 그냥 gridview 세팅/////////////////////////
        gridView = (GridView) findViewById(R.id.gv_mainpage);
        mainGridAdapter = new MainGridViewAdapter(this, R.layout.row_grid_mainpage, gridArray);
        gridView.setAdapter(mainGridAdapter);

        bt_login.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        bt_mypage.setOnClickListener(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void changeLoginState(boolean isLogin) {
        if (isLogin) { // 로그인이 되어있을 경우
            bt_login.setVisibility(View.GONE);
            tv_nickname.setText(SaveSharedPreference.getNickname() + "님");
        } else { // 로그인이 안되어있을 경우
            cl_inLogin.setVisibility(View.GONE);
        }
    }

    @Override
    public void moveLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startActivity(new Intent(NewMainActivity.this, LoginActivity.class));
                break;
            case R.id.aam_btn_logout:
                presenter.logout();
                break;
            case R.id.bt_mypage:
                startActivity(new Intent(NewMainActivity.this, MyPageActivity.class));
                break;

        }
    }
}