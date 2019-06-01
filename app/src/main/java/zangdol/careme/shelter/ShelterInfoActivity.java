package zangdol.careme.shelter;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.Shelter;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;
import zangdol.careme.util.mapMarker.OnMapLoadListener;
import zangdol.careme.util.mapMarker.ShowMapWithMarker;

public class ShelterInfoActivity extends AppCompatActivity implements ShelterInfoContract.View, View.OnClickListener {
    private ShelterInfoPresenter presenter;
    private TextView name;
    private TextView address;
    private TextView phoneNumber;
    private TextView workingHours;
    private TextView description;

    private Button animalListBt;
    private ShelterInfoActivity me;

    private ImageView iv_shelterImage;

    private ShowMapWithMarker map;

    /*
        private MapFragment mapFragment;
        private NaverMap naverMap;

        private OnNaverMapListener listener;
        private interface OnNaverMapListener{
            void onNaverMap();
        }
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelterinfo);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_shelter_info);

        presenter = new ShelterInfoPresenter(this);

        me = this;
        setItem();

        presenter.getShelter();
    }

    public void setItem() {
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phoneNumber = (TextView) findViewById(R.id.phoneNumber);
        workingHours = (TextView) findViewById(R.id.textView33);
        description = (TextView) findViewById(R.id.textView35);

        animalListBt = (Button) findViewById(R.id.animalListBt);

        iv_shelterImage = (ImageView) findViewById(R.id.si_sf_shelter_image);
        animalListBt.setOnClickListener(this);

        map = new ShowMapWithMarker(this,R.id.si_map);
/*
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.si_map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.si_map, mapFragment).commit();
        }
*/

    }

    @Override
    public void setShelter(final Shelter shelter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                name.setText(shelter.getName());
                address.setText(shelter.getPosition());
                phoneNumber.setText(shelter.getPnum());
                NullChecker.image(shelter.getUrl_picture(), iv_shelterImage);

                if (!shelter.getStartTime().equals("null") && !shelter.getEndTime().equals("null"))
                    workingHours.setText(ConvertManager.time(shelter.getStartTime()) + " ~ " + ConvertManager.time(shelter.getEndTime()));

                NullChecker.text(shelter.getDescription(), description, "입력안됨");

                map.initialize(new OnMapLoadListener() {

                    @Override
                    public ShowMapWithMarker getShowMapWithMarker() {
                        return map;
                    }

                    @Override
                    public String getLatitude() {
                        return shelter.getLatitude();
                    }

                    @Override
                    public String getLongitude() {
                        return shelter.getLongitude();
                    }
                });
/*
                listener = new OnNaverMapListener() {
                    @Override
                    public void onNaverMap() {
                        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(Double.parseDouble(shelter.getLatitude()), Double.parseDouble(shelter.getLongitude())));
                        naverMap.moveCamera(cameraUpdate);

                        Marker marker = new Marker();
                        marker.setPosition(new LatLng(Double.parseDouble(shelter.getLatitude()), Double.parseDouble(shelter.getLongitude())));
                        marker.setMap(naverMap);
                    }
                };
                mapFragment.getMapAsync(me);
*/


            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animalListBt:
                presenter.moveAnimalList();
                break;
        }
    }
/*
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        listener.onNaverMap();
    }*/
}
