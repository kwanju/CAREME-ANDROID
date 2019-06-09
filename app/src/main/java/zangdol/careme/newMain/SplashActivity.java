package zangdol.careme.newMain;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import zangdol.careme.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent LoginActivity = new Intent(SplashActivity.this, NewMainActivity.class);
                startActivity(LoginActivity);
                overridePendingTransition(0, 0);
                finish();
            }
        }, 1000);
    }
}
