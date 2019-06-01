package zangdol.careme.bulletinBoardDiscoverFind.discoverFind;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.Discover;
import zangdol.careme.model.Find;
import zangdol.careme.shelter.ShelterInfoActivity;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;
import zangdol.careme.util.mapMarker.OnMapLoadListener;
import zangdol.careme.util.mapMarker.ShowMapWithMarker;

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

    private LinearLayout ll_matching;
    private TextView tv_shelter;

    private DiscoverFindActivity me;

    private ShowMapWithMarker map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_information);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_dog_information);

        presenter = new DiscoverFindPresenter(this);
        setItem();
        map = new ShowMapWithMarker(this, R.id.di_map);
        presenter.getData();
        me = this;


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

        ll_matching = (LinearLayout) findViewById(R.id.adi_ll_matching);
        tv_shelter = (TextView) findViewById(R.id.adi_tv_shelter);
    }


    @Override
    public void setDiscoverData(final Discover discover) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                status.setText("발견했어요");
                status.setBackgroundColor(0x6A5ACD00);

                NullChecker.image(discover.getUrl_picture(), doggyImage);
                NullChecker.text(ConvertManager.getSpecies(discover.getSpeciesCode()), type);

                if (discover.getAnimalSex().equals(""))
                    sex.setText("성별 입력안됨");
                else if (discover.getAnimalSex().equals("w"))
                    sex.setText("암컷");
                else
                    sex.setText("수컷");

                NullChecker.text(discover.getDescription(), detailDescription);
                lostDate.setText(ConvertManager.date(discover.getEventDateTime(), ConvertManager.DATETIME));
                NullChecker.text(discover.getEventSpot(), lostPlace);
                NullChecker.text(discover.getRegisterNickname(), id);
                NullChecker.text(discover.getRegisterPhoneNumber(), phoneNumber);

                NullChecker.text(discover.getShelterName(), tv_shelter, "매칭중");

                if (!discover.getShelterName().equals("null")) {
                    tv_shelter.setTextColor(Color.BLUE);
                    tv_shelter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(me, ShelterInfoActivity.class);
                            intent.putExtra("idx", discover.getMatchingShelterIdx());
                            me.startActivity(intent);
                        }
                    });
                }

                map.initialize(new OnMapLoadListener() {
                    @Override
                    public ShowMapWithMarker getShowMapWithMarker() {
                        return map;
                    }

                    @Override
                    public String getLatitude() {
                        return discover.getLatitude();
                    }

                    @Override
                    public String getLongitude() {
                        return discover.getLongitude();
                    }
                });

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
                NullChecker.text(ConvertManager.getSpecies(find.getSpeciesCode()), type);

                if (find.getAnimalSex().equals(""))
                    sex.setText("성별 입력안됨");
                else if (find.getAnimalSex().equals("w"))
                    sex.setText("암컷");
                else
                    sex.setText("수컷");

                NullChecker.text(find.getDescription(), detailDescription);
                lostDate.setText(ConvertManager.date(find.getEventDateTime(), ConvertManager.DATETIME));
                NullChecker.text(find.getEventSpot(), lostPlace);
                NullChecker.text(find.getRegisterNickname(), id);
                NullChecker.text(find.getRegisterPhoneNumber(), phoneNumber);

                ll_matching.setVisibility(View.GONE);

                map.initialize(new OnMapLoadListener() {
                    @Override
                    public ShowMapWithMarker getShowMapWithMarker() {
                        return map;
                    }

                    @Override
                    public String getLatitude() {
                        return find.getLatitude();
                    }

                    @Override
                    public String getLongitude() {
                        return find.getLongitude();
                    }
                });
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}