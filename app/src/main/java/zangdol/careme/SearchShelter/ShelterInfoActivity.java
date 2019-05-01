package zangdol.careme.SearchShelter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import zangdol.careme.R;

public class ShelterInfoActivity extends AppCompatActivity
{
    TextView name;
    TextView address;
    TextView phoneNumber;

    Button enrollFavoriteBt;
    Button animalListBt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterinfo);

        setItem();
//////////이전 화면에서 선택된 유기견보호소의 이름을 추출하고 서버에 보내 보호소의 정보를 받아온다.//////////////////
////////// 유기견 정보에는 유기견보호소명, 주소, 전화번호, 사진이 되겠다.////////////////////////////////////////////
        //여기서는 임의의 유기견보호소 정보를 적겠다.
        name.setText( " 유기견 보호 센터 " );
        address.setText( "Blah Bla Bla Blah" );
        phoneNumber.setText( "xxx-xxx-xxxx" );

    }
    public void setItem()
    {
        name = (TextView)findViewById( R.id.name );
        address = (TextView)findViewById( R.id.address );
        phoneNumber = (TextView)findViewById( R.id.phoneNumber );

        enrollFavoriteBt = (Button)findViewById( R.id.enrollFavoriteBt );
        animalListBt = (Button)findViewById( R.id.animalListBt );
    }

}
