package zangdol.careme.newMain;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import zangdol.careme.R;
import zangdol.careme.SearchShelter.SearchShelterCategoryActivity;
import zangdol.careme.bulletinBoardDiscoverFind.BulletinBoardDiscoverFindActivity;
import zangdol.careme.login.LoginActivity;
import zangdol.careme.model.ItemForMain;
import zangdol.careme.myPage.MyPageActivity;
import zangdol.careme.searchFilterDogs.SearchFilterDogsActivity;

public class AlternativeMainActivity extends AppCompatActivity
{
    private Button bt_login;
    private Button bt_mypage;
    private Button bt_logout;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    GridView gridView;
    ArrayList<ItemForMain> gridArray = new ArrayList<ItemForMain>();
    MainGridViewAdapter mainGridAdapter;
    //그리드뷰에 이용될 것들...

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternativemain);

        setItem();

///////////////////////로그인 상태가 아니라면///////////////////////////////////////////
        bt_login.setVisibility( View.VISIBLE);
        bt_logout.setVisibility( View.INVISIBLE);
///////////////////////로그인 상태라면/////////////////////////////
        //bt_login.setVisibility( View.INVISIBLE);
        //bt_logout.setVisibility( View.VISIBLE);
/////////////////////////////if문 쓰셈..///////////////////////////////

        bt_login.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AlternativeMainActivity.this, LoginActivity.class));
////////////////////로그인 화면으로 넘어간다.//////////////////
            }
        });

        bt_logout.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
////////////////////로그아웃을 한다.//////////////////
                Toast.makeText(AlternativeMainActivity.this, "로그아웃합니다.", Toast.LENGTH_SHORT).show();
                finish();//또는 메인화면으로 간다.
///////////////////////로그아웃 상태로 만든다.///////////////////////////////////////
            }
        });

        bt_mypage.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AlternativeMainActivity.this, MyPageActivity.class));
////////////////////마이페이지 화면으로 넘어간다.//////////////////
            }
        });


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        //네비게이션 뷰에서 아이템 클릭 객체 생성
    //    navigationView.setNavigationItemSelectedListener(this);

        Bitmap shelterIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.shelter_main);
        Bitmap dogIcon = BitmapFactory.decodeResource(this.getResources(), R.drawable.dog_main);
        Bitmap lost_found_Icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.lost_found_main);
//////////////////////각각의 기능의 아이콘을 만들어 아래 gridArray에 기능명과 함께 add한다. /////////////////////
        gridArray.add(new ItemForMain(shelterIcon,"보호소찾기"));
        gridArray.add(new ItemForMain(lost_found_Icon,"발견했어요/찾아요"));
        gridArray.add(new ItemForMain(dogIcon,"유기동물 검색"));



        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                ItemForMain itemForMain = gridArray.get(position);
                Toast.makeText(AlternativeMainActivity.this,  "index"+position +"" + itemForMain.getFunctionName(), Toast.LENGTH_SHORT).show();
            if(position == 0)
                startActivity(new Intent(AlternativeMainActivity.this, SearchShelterCategoryActivity.class));
            else if(position == 1)
                startActivity(new Intent( AlternativeMainActivity.this, BulletinBoardDiscoverFindActivity.class));
            else if(position ==2)
                startActivity(new Intent( AlternativeMainActivity.this, SearchFilterDogsActivity.class));
            }
        });

    }
    public void setItem()
    {
        bt_login = (Button)findViewById(R.id.login_button);
        bt_mypage = (Button)findViewById(R.id.bt_mypage);
        bt_logout = (Button)findViewById(R.id.logout_button);

        navigationView = findViewById(R.id.navigation_view);
        // 드로어 토글 버튼 생성
        drawerLayout = findViewById(R.id.drawerLayout);

        ////////////////////////////이건 그냥 gridview 세팅/////////////////////////
        gridView = (GridView) findViewById(R.id.gv_mainpage);
        mainGridAdapter = new MainGridViewAdapter(this, R.layout.row_grid_mainpage, gridArray);
        gridView.setAdapter(mainGridAdapter);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}