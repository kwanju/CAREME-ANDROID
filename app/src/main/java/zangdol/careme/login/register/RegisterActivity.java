package zangdol.careme.login.register;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import zangdol.careme.R;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener{

    private RegisterPresenter presenter;

    private MaterialEditText et_id;
    private MaterialEditText et_pw;
    private MaterialEditText et_nickname;
    private MaterialEditText et_phoneNumber;
    private MaterialEditText et_email;

    private Button bt_checkDupId;

    private BootstrapButton bt_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        presenter = new RegisterPresenter(this);

        setElements();

    }

    private void setElements() {
        et_id = (MaterialEditText) findViewById(R.id.register_id);
        et_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.setCheckDupIDForFalse(); // ID가 새로 입력되는 순간 다시 아이디를 체크해야하도록 만들기 위해.
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_pw = (MaterialEditText) findViewById(R.id.register_pw);
        et_email = (MaterialEditText) findViewById(R.id.register_email);
        et_nickname = (MaterialEditText) findViewById(R.id.register_nickname);
        et_phoneNumber = (MaterialEditText) findViewById(R.id.register_phone_number);

        bt_checkDupId = (Button) findViewById(R.id.register_check_dup_id);
        bt_checkDupId.setOnClickListener(this);

        bt_register = (BootstrapButton) findViewById(R.id.register_btn_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_check_dup_id:
                presenter.checkDupId(et_id.getText().toString());
                break;
            case R.id.register_btn_register:
                presenter.register(
                        et_id.getText().toString(),
                        et_pw.getText().toString(),
                        et_nickname.getText().toString(),
                        et_phoneNumber.getText().toString(),
                        et_email.getText().toString());
                break;
        }
    }

}
