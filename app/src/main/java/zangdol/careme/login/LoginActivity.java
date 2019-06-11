package zangdol.careme.login;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import zangdol.careme.R;
import zangdol.careme.main.MainActivity;
import zangdol.careme.newMain.NewMainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private MaterialEditText et_id;
    private MaterialEditText et_pw;
    private BootstrapButton btn_login;
    private TextView btn_registration;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_login);

        setElement();
        setListener();
        loginPresenter = new LoginPresenter(this); // set Controller
    }

    private void setElement() {
        et_id = (MaterialEditText) findViewById(R.id.login_id);
        et_pw = (MaterialEditText) findViewById(R.id.login_pw);
        et_pw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btn_login = (BootstrapButton) findViewById(R.id.login_login);
        btn_registration = (TextView) findViewById(R.id.login_registration);
        btn_registration.setPaintFlags(btn_registration.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void setListener() {
        btn_login.setOnClickListener(this);
        btn_registration.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                loginPresenter.login(et_id.getText().toString(), et_pw.getText().toString());
                break;
            case R.id.login_registration:
                loginPresenter.register();
                break;
        }
    }

    @Override
    public void moveMainActivity() {
        Intent intent = new Intent(this, NewMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
