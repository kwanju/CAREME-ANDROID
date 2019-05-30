package zangdol.careme.shelter;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.Shelter;
import zangdol.careme.util.NullChecker;

public class ShelterInfoActivity extends AppCompatActivity implements ShelterInfoContract.View, View.OnClickListener {
    private ShelterInfoPresenter presenter;
    private TextView name;
    private TextView address;
    private TextView phoneNumber;
    private TextView workingHours;
    private TextView description;

    private Button enrollFavoriteBt;
    private Button animalListBt;

    private ImageView iv_shelterImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterinfo);

        presenter = new ShelterInfoPresenter(this);


        setItem();

        presenter.getShelter();
//////////이전 화면에서 선택된 유기견보호소의 이름을 추출하고 서버에 보내 보호소의 정보를 받아온다.//////////////////
////////// 유기견 정보에는 유기견보호소명, 주소, 전화번호, 사진이 되겠다.////////////////////////////////////////////
        //여기서는 임의의 유기견보호소 정보를 적겠다.
        name.setText(" 유기견 보호 센터 ");
        address.setText("Blah Bla Bla Blah");
        phoneNumber.setText("xxx-xxx-xxxx");

    }

    public void setItem() {
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        workingHours = (TextView)findViewById(R.id.textView33);
        description = (TextView)findViewById(R.id.textView35);

        enrollFavoriteBt = (Button) findViewById(R.id.enrollFavoriteBt);
        animalListBt = (Button) findViewById(R.id.animalListBt);

        iv_shelterImage = (ImageView) findViewById(R.id.si_sf_shelter_image);
        animalListBt.setOnClickListener(this);
    }

    @Override
    public void setShelter(final Shelter shelter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                name.setText(shelter.getName());
                address.setText(shelter.getPosition());
                phoneNumber.setText(shelter.getPnum());
                NullChecker.image(shelter.getUrl_picture(),iv_shelterImage);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animalListBt:
                presenter.moveAnimalList();
                break;
        }
    }
}
