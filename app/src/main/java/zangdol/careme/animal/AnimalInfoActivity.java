package zangdol.careme.animal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import zangdol.careme.R;
import zangdol.careme.model.Animal;
import zangdol.careme.util.ConvertManager;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_info);
        me = this;
        presenter = new AnimalInfoPresenter(this);
        setElements();
        presenter.getAnimalInfo(getIntent().getIntExtra("idx",0)); // 동물 정보를 가져옴
    }

    private void setElements() {

        iv_image = (ImageView) findViewById(R.id.animal_info_image);

        tv_idx = (TextView) findViewById(R.id.animal_info_idx);
        tv_name = (TextView) findViewById(R.id.animal_info_name);
        tv_species = (TextView) findViewById(R.id.animal_info_species);
        tv_sex = (TextView) findViewById(R.id.animal_info_sex);
        tv_discovered_spot = (TextView) findViewById(R.id.animal_info_discovered_spot);
        tv_shelterIdx = (TextView) findViewById(R.id.animal_info_shelter_idx);
        tv_description = (TextView) findViewById(R.id.animal_info_description);

        bt_applyVolunteer = (BootstrapButton) findViewById(R.id.animal_info_apply_volunteer);
        bt_applyVolunteer.setOnClickListener(this);
    }

    @Override
    public void setAnimalInfo(final Animal animal) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                NullChecker.image(animal.getUrlPicture(), iv_image);
                NullChecker.text(animal.getName(), tv_name);
                NullChecker.text(ConvertManager.getSpecies(animal.getSpeciesCode()), tv_species);
                NullChecker.text(animal.getDiscoveredSpot(), tv_discovered_spot);
                NullChecker.text(animal.getShelterIdx(), tv_shelterIdx);
                NullChecker.text(animal.getDescription(), tv_description);
                if (animal.getSex()=='m')
                    tv_sex.setText("남");
                else if (animal.getSex()=='w')
                    tv_sex.setText("여");
                else
                    tv_sex.setText("입력안됨");
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.animal_info_apply_volunteer:
                presenter.moveApplyVolunteer();
                break;
        }
    }
}
