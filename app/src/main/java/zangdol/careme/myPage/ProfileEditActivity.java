package zangdol.careme.myPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import zangdol.careme.R;

public class ProfileEditActivity extends AppCompatActivity
{
    private EditText et_nickname;
    private EditText et_email;
    private EditText et_phone;
    private EditText et_intro;
    private ImageButton imgbt_profile;
    private ImageButton imgbt_background;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        setItem();
    }
    public void onClickProfile(View view)
    {
        //imgbt_profile에 갤러리에서 사진을 선택, 가져와서 박아놓는다.
    }
    public void onClickBackground(View view)
    {
        //imgbt_background에 갤러리에서 사진을 선택, 가져와서 박아놓는다.

    }
    public void onClickEdit(View view)
    {
        // 여섯개의 view에서 정보를 뜯어와서 서버에 보내 프로필정보를 바꾼다.
       /*
        et_nickname;
        et_email;
        et_phone;
        et_intro;
        imgbt_profile;
        imgbt_background;
        */
    }
    public void setItem()
    {
        et_nickname = (EditText)findViewById( R.id.et_nickname );
        et_email = (EditText)findViewById(R.id.et_email);
        et_phone = (EditText)findViewById(R.id.et_phone);
        et_intro = (EditText)findViewById(R.id.et_intro);
        imgbt_profile = (ImageButton)findViewById(R.id.imgbt_profile);
        imgbt_background = (ImageButton)findViewById(R.id.imgbt_background);
    }

}