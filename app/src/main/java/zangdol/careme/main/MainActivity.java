package zangdol.careme.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import zangdol.careme.R;
import zangdol.careme.SearchShelter.SearchShelterCategoryActivity;
import zangdol.careme.bulletinBoardDiscoverFind.BulletinBoardDiscoverFindActivity;
import zangdol.careme.discoverRecord.DiscoverRecordActivity;
import zangdol.careme.registerDiscoverFind.RegisterDiscoverFindActivity;
import zangdol.careme.login.LoginActivity;
import zangdol.careme.searchFilterDogs.SearchFilterDogsActivity;
import zangdol.careme.util.SaveSharedPreference;
import zangdol.careme.volunteerRecord.VolunteerRecordActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainContract.View {

    public static Context contextOfApplication; // 공통의 정보를 저장하기 위해서.
    private MainPresenter mainPresenter;

    private Button bt_login;
    private Button bt_logout;
    private Button bt_searchSheter;
    private Button bt_test;
    private BootstrapButton bt_volunteerRecord;
    private Button bt_myPage;
    private Button bt_test2;
    private BootstrapButton bt_BulletinBoardDiscoverFind;
    private BootstrapButton bt_discoverRecord;
    private BootstrapButton bt_findAnimal;

    private TextView tv_id;

    private LinearLayout layout_login;
    private LinearLayout layout_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();
        setListener();
        mainPresenter = new MainPresenter(this);

        contextOfApplication = getApplicationContext();

        mainPresenter.checkLogin();

        Log.d("MAINTEST", "googoo");
        Log.d("MAINTEST", SaveSharedPreference.getToken());
    }

    private void setElements() {
        bt_login = (Button) findViewById(R.id.button_login);
        bt_logout = (Button) findViewById(R.id.button_logout);
        bt_searchSheter = (Button) findViewById(R.id.button_search_shelter);
        bt_test = (Button) findViewById(R.id.button_test);
        bt_volunteerRecord = (BootstrapButton) findViewById(R.id.button_volunteer_record);
        bt_myPage = (Button) findViewById( R.id.mypage );
        bt_test2 = (Button)findViewById(R.id.testtest);
        bt_BulletinBoardDiscoverFind = (BootstrapButton) findViewById(R.id.button_discover_find_bulletin_board);
        bt_discoverRecord = (BootstrapButton) findViewById(R.id.button_discover_record);
        bt_findAnimal = (BootstrapButton) findViewById(R.id.button_find_animal);
        tv_id = (TextView) findViewById(R.id.tv_id);

        layout_login = (LinearLayout) findViewById(R.id.layout_login);
        layout_logout = (LinearLayout) findViewById(R.id.layout_logout);
    }

    private void setListener() {
        bt_login.setOnClickListener(this);
        bt_logout.setOnClickListener(this);
        bt_searchSheter.setOnClickListener(this);
        bt_test.setOnClickListener(this);
        bt_volunteerRecord.setOnClickListener(this);
        bt_test2.setOnClickListener(this);
        bt_BulletinBoardDiscoverFind.setOnClickListener(this);
        bt_discoverRecord.setOnClickListener(this);
        bt_findAnimal.setOnClickListener(this);
    }


    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                moveLoginActivity();
                break;

            case R.id.button_logout:
                mainPresenter.logout();
                break;

            case R.id.button_search_shelter:
                Intent intent = new Intent(this, SearchShelterCategoryActivity.class);
                startActivity(intent);
                break;
            case R.id.button_test:
                startActivity(new Intent(this, SearchFilterDogsActivity.class));
                break;
            case R.id.button_volunteer_record:
                startActivity(new Intent(this, VolunteerRecordActivity.class));
                break;
            case R.id.testtest:
                startActivity(new Intent(this, RegisterDiscoverFindActivity.class));
                break;
            case R.id.button_discover_find_bulletin_board:
                startActivity(new Intent(this,BulletinBoardDiscoverFindActivity.class));
                break;
            case R.id.button_discover_record:
                startActivity(new Intent(this,DiscoverRecordActivity.class));
                break;
            case R.id.button_find_animal:
                startActivity(new Intent(this,SearchFilterDogsActivity.class));
                break;
        }
    }

    @Override
    public void changeLoginState(boolean isLogin) {
        if (isLogin) { // 로그인이 되어있을 경우
            layout_login.setVisibility(View.GONE);
            tv_id.setText(SaveSharedPreference.getNickname() + "님");
        } else { // 로그인이 안되어있을 경우
            layout_logout.setVisibility(View.GONE);
        }
    }

    @Override
    public void moveLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
