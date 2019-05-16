package zangdol.careme.lostFound;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import zangdol.careme.R;

public class DogInformationActivity extends AppCompatActivity
{
    private ImageView doggyImage;
    private TextView status;
    private TextView type;
    private TextView sex;
    private TextView age;
    private TextView weight;
    private TextView hairColor;
    private TextView features;
    private TextView lostDate;
    private TextView lostPlace;
    private TextView id;
    private TextView phoneNumber;
    private TextView detailDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_information);

        setItem();
//////////////////////////////////// 유기견을 실종다거나 발견했거나 보호중일 때 전에 올린 해당 글들 정보 뜯어와서 아래에 setInfo()의 파라미터에 쑤셔넣기/////////
///////////////////////////////////여기서는 임의로 넣겠다.////////////////////////////////////////////       /
        setInfo( "강아지 사진 URL", "실종", "말티즈", "수컷", "5살", "4kg", "화이트",
"어렸을때 미용하다가 왼쪽 귀가 3분의 1정도 짤렸습니다. 귀털때문에 가려졌지만 만지면 쉽게 구분 가능합니다. 피부병치료중입니다.",
                "2019-05-14", "서울특별시 은평구 은평구 대조동", "쵸르비스", "010-xxxx-xxxx",
"5월 14일 밤 10시쯤 대조공원 산책하다 잃어버렸습니다. 잃어버렸을 당시엔 아이보리색 등줄을 착용했습니다. 찾아주시면 사례하겠습니다." );

    }
    public void setItem()
    {
        doggyImage = (ImageView)findViewById( R.id.doggyimage );
        status = (TextView)findViewById( R.id.tv_status_dogInfo );
        type = (TextView)findViewById( R.id.tv_type_dogInfo );
        sex = (TextView)findViewById( R.id.tv_sex_dog );
        age = (TextView)findViewById( R.id.tv_age_dog );
        weight = (TextView)findViewById( R.id.tv_weight_dog );
        hairColor = (TextView)findViewById( R.id.tv_haircolor_dog );
        features = (TextView)findViewById( R.id.tv_feature_dog );
        lostDate = (TextView)findViewById( R.id.tv_lostdate_dog );
        lostPlace = (TextView)findViewById( R.id.tv_lostplace_dog );
        id = (TextView)findViewById( R.id.tv_id_owner );
        phoneNumber = (TextView)findViewById( R.id.tv_phone_owner );
        detailDescription = (TextView)findViewById( R.id.tv_detail );


    }
    public void setInfo( String imgURL, String status,String type, String sex, String age, String weight, String hairColor, String features, String date,
                                    String place, String id, String phoneNumber, String detail)
    {
///////////////////////////////doggyImage에 String imgURL을 받아와서 박아넣는다.////////////////////////////////////////////////
        this.status.setText( status ); this.type.setText( type ); this.sex.setText( sex ); this.age.setText(age);
        this.weight.setText( weight ); this.hairColor.setText( hairColor ); this.features.setText( features);
        lostDate.setText( date ); lostPlace.setText( place ); this.id.setText(id); this.phoneNumber.setText( phoneNumber );
        detailDescription.setText( detail );

        if( this.status.getText().equals("실종") )
            this.status.setBackgroundColor( 0xFFFFA500 );
        else if( this.status.getText().equals( "목격" ))
            this.status.setBackgroundColor( 0xFFFFFF00 );
        else
            this.status.setBackgroundColor( 0xFF009900 );

    }
}