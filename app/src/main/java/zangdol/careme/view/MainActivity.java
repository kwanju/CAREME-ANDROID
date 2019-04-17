package zangdol.careme.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.presenter.MainController;
import zangdol.careme.util.SaveSharedPreference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static Context contextOfApplication; // 공통의 정보를 저장하기 위해서.
    private MainController mainController;

    private Button bt_login;
    private Button bt_logout;

    private TextView tv_id;

    private LinearLayout layout_login;
    private LinearLayout layout_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();
        setListener();
        mainController = new MainController(this);

        contextOfApplication = getApplicationContext();

        checkLogin();
    }

    private void setElements() {
        bt_login = (Button) findViewById(R.id.button_login);
        bt_logout = (Button) findViewById(R.id.button_logout);
        tv_id = (TextView) findViewById(R.id.tv_id);
        layout_login = (LinearLayout) findViewById(R.id.layout_login);
        layout_logout = (LinearLayout) findViewById(R.id.layout_logout);
    }

    private void setListener() {
        bt_login.setOnClickListener(this);
        bt_logout.setOnClickListener(this);
    }

    private void checkLogin() {
        if (SaveSharedPreference.getID().length() == 0) { // 로그인이 안되어있을 경우
            layout_logout.setVisibility(View.GONE);
        } else { // 로그인이 되어있을 경우
            layout_login.setVisibility(View.GONE);
            tv_id.setText(SaveSharedPreference.getID()+"님");
        }
    }

    public static Context getContextOfApplication() {
        return contextOfApplication;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                mainController.moveLogin();
                break;

            case R.id.button_logout:
                mainController.logout();
                break;
        }
    }
}
