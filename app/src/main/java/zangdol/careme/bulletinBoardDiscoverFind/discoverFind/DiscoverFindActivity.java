package zangdol.careme.bulletinBoardDiscoverFind.discoverFind;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.Discover;
import zangdol.careme.model.Find;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class DiscoverFindActivity extends AppCompatActivity implements DiscoverFindContract.View {
    private DiscoverFindContract.Presenter presenter;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_information);
        presenter = new DiscoverFindPresenter(this);
        setItem();
        presenter.getData();

    }

    public void setItem() {
        doggyImage = (ImageView) findViewById(R.id.doggyimage);
        status = (TextView) findViewById(R.id.tv_status_dogInfo);
        type = (TextView) findViewById(R.id.tv_type_dogInfo);
        sex = (TextView) findViewById(R.id.tv_sex_dog);
        lostDate = (TextView) findViewById(R.id.tv_lostdate_dog);
        lostPlace = (TextView) findViewById(R.id.tv_lostplace_dog);
        id = (TextView) findViewById(R.id.tv_id_owner);
        phoneNumber = (TextView) findViewById(R.id.tv_phone_owner);
        detailDescription = (TextView) findViewById(R.id.tv_detail);


    }


    @Override
    public void setDiscoverData(final Discover discover) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                status.setText("발견했어요");
                status.setBackgroundColor(0x6A5ACD00);

                NullChecker.image(discover.getUrl_picture(), doggyImage);
                NullChecker.text(discover.getSpeciesCode(), type);

                if(discover.getAnimalSex().equals(""))
                    sex.setText("성별 입력안됨");
                else if (discover.getAnimalSex().equals("w"))
                    sex.setText("암컷");
                else
                    sex.setText("수컷");

                NullChecker.text(discover.getDescription(), detailDescription);
                lostDate.setText(ConvertManager.date(discover.getEventDateTime(),ConvertManager.DATETIME));
                NullChecker.text(discover.getEventSpot(), lostPlace);
                NullChecker.text(discover.getRegisterNickname(),id);
                NullChecker.text(discover.getRegisterPhoneNumber(),phoneNumber);
            }
        });

    }

    @Override
    public void setFindData(final Find find) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                status.setText("찾아요");
                status.setBackgroundColor(0x90FFA500);

                NullChecker.image(find.getUrl_picture(), doggyImage);
                NullChecker.text(find.getSpeciesCode(), type);

                if(find.getAnimalSex().equals(""))
                    sex.setText("성별 입력안됨");
                else if (find.getAnimalSex().equals("w"))
                    sex.setText("암컷");
                else
                    sex.setText("수컷");

                NullChecker.text(find.getDescription(), detailDescription);
                lostDate.setText(ConvertManager.date(find.getEventDateTime(),ConvertManager.DATETIME));
                NullChecker.text(find.getEventSpot(), lostPlace);
                NullChecker.text(find.getRegisterNickname(),id);
                NullChecker.text(find.getRegisterPhoneNumber(),phoneNumber);
            }
        });
    }
}