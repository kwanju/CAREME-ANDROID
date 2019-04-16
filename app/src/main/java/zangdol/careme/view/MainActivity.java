package zangdol.careme.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import zangdol.careme.R;
import zangdol.careme.controller.MainController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MainController mainController;

    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setElements();
        setListener();
        mainController = new MainController(this);
    }

    private void setElements() {
        bt_login = (Button) findViewById(R.id.main_login);
    }

    private void setListener() {
        bt_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_login:
                mainController.moveLogin();
                break;
        }
    }
}
