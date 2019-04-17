package zangdol.careme.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import zangdol.careme.R;
import zangdol.careme.presenter.LoginController;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_id;
    private EditText et_pw;
    private Button btn_login;

    private LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setElement();
        setListener();
        loginController = new LoginController(this); // set Controller
    }

    private void setElement() {
        et_id = (EditText) findViewById(R.id.login_id);
        et_pw = (EditText) findViewById(R.id.login_pw);
        btn_login = (Button) findViewById(R.id.login_login);
    }

    private void setListener() {
        btn_login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_login:
                loginController.login(et_id.getText().toString(), et_pw.getText().toString());
                break;
        }
    }
}
