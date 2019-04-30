package zangdol.careme.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import zangdol.careme.R;
import zangdol.careme.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginContract.View {

    private MaterialEditText et_id;
    private MaterialEditText et_pw;
    private BootstrapButton btn_login;
    private BootstrapButton btn_registration;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setElement();
        setListener();
        loginPresenter = new LoginPresenter(this); // set Controller
    }

    private void setElement() {
        et_id = (MaterialEditText) findViewById(R.id.login_id);
        et_pw = (MaterialEditText) findViewById(R.id.login_pw);
        et_pw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        btn_login = (BootstrapButton) findViewById(R.id.login_login);
        btn_registration = (BootstrapButton) findViewById(R.id.login_registration);
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
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
