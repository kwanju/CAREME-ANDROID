package zangdol.careme.animal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import zangdol.careme.R;
import zangdol.careme.model.Animal;
import zangdol.careme.shelter.ShelterInfoActivity;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.DBHelper;
import zangdol.careme.util.NullChecker;

public class AnimalInfoActivity extends AppCompatActivity implements AnimalInfoContract.View, View.OnClickListener {
    private AnimalInfoPresenter presenter;
    private AnimalInfoActivity me;

    private ImageView iv_image;

    private TextView tv_idx;
    private TextView tv_name;
    private TextView tv_species;
    private TextView tv_sex;
    private TextView tv_discovered_spot;
    private TextView tv_shelterIdx;
    private TextView tv_description;

    private BootstrapButton bt_applyVolunteer;

    private DBHelper dbHelper;

    private ImageView iv_like;
    private LinearLayout ll_like;

    private int animal_idx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        me = this;
        presenter = new AnimalInfoPresenter(this);
        setElements();

        dbHelper = new DBHelper(this, DBHelper.DBNAME, null, DBHelper.DBVERSION);
        animal_idx = getIntent().getIntExtra("idx", 0);
        presenter.getAnimalInfo(animal_idx); // 동물 정보를 가져옴
    }

    private void setElements() {

        iv_image = (ImageView) findViewById(R.id.animal_info_image);
        iv_like = (ImageView) findViewById(R.id.ai_iv_like);
        ll_like = (LinearLayout) findViewById(R.id.ai_ll_like);

        tv_idx = (TextView) findViewById(R.id.animal_info_idx);
        tv_name = (TextView) findViewById(R.id.animal_info_name);
        tv_species = (TextView) findViewById(R.id.animal_info_species);
        tv_sex = (TextView) findViewById(R.id.animal_info_sex);
        tv_discovered_spot = (TextView) findViewById(R.id.animal_info_discovered_spot);
        tv_shelterIdx = (TextView) findViewById(R.id.animal_info_shelter_idx);
        tv_description = (TextView) findViewById(R.id.animal_info_description);

        bt_applyVolunteer = (BootstrapButton) findViewById(R.id.animal_info_apply_volunteer);
        bt_applyVolunteer.setOnClickListener(this);
        ll_like.setOnClickListener(this);
    }

    @Override
    public void setAnimalInfo(final Animal animal) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (dbHelper.IsExist(animal.getIdx()))
                    iv_like.setImageDrawable(getApplicationContext().getDrawable(R.drawable.like_full));
                else
                    iv_like.setImageDrawable(getApplicationContext().getDrawable(R.drawable.like_empty));
                NullChecker.image(animal.getUrlPicture(), iv_image);
                NullChecker.text(animal.getName(), tv_name);
                NullChecker.text(ConvertManager.getSpecies(animal.getSpeciesCode()), tv_species);
                NullChecker.text(animal.getDiscoveredSpot(), tv_discovered_spot);
                NullChecker.text(animal.getShelterName(), tv_shelterIdx);
                NullChecker.text(animal.getDescription(), tv_description);
                if (animal.getSex() == 'm')
                    tv_sex.setText("남");
                else if (animal.getSex() == 'w')
                    tv_sex.setText("여");
                else
                    tv_sex.setText("입력안됨");

                tv_shelterIdx.setTextColor(Color.BLUE);
                tv_shelterIdx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(me, ShelterInfoActivity.class);
                        intent.putExtra("idx", animal.getShelterIdx());
                        me.startActivity(intent);
                    }
                });
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animal_info_apply_volunteer:
                presenter.moveApplyVolunteer();
                break;
            case R.id.ai_ll_like:
                Log.d("TEST", "IN");
                if (dbHelper.IsExist(String.valueOf(animal_idx))) {
                    dbHelper.delete(String.valueOf(animal_idx));
                    iv_like.setImageDrawable(getApplicationContext().getDrawable(R.drawable.like_empty));
                } else {
                    dbHelper.insert(String.valueOf(animal_idx));
                    iv_like.setImageDrawable(getApplicationContext().getDrawable(R.drawable.like_full));
                }
                break;
        }
    }
}
