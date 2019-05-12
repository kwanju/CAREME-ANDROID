package zangdol.careme.map;


import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

import zangdol.careme.R;

public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, MapViewContract.View {
    private String tag = "mapViewTest";

    private MapFragment mapFragment;
    private NaverMap naverMap;

    private LinearLayout lv_layout;
    private ImageView iv_centerMarker;
    private BootstrapButton bt_setPosition;

    private MapViewContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setElements();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //Here you can get the size!
        int[] location = new int[2];
        lv_layout.getLocationOnScreen(location);
        iv_centerMarker.setTranslationX(lv_layout.getMeasuredWidth() / 2 - iv_centerMarker.getMeasuredWidth() / 2);
        iv_centerMarker.setTranslationY(lv_layout.getMeasuredHeight() / 2 - iv_centerMarker.getMeasuredHeight());

        bt_setPosition.setTranslationX(lv_layout.getMeasuredWidth() / 2 - bt_setPosition.getMeasuredWidth() / 2);
        bt_setPosition.setTranslationY(lv_layout.getMeasuredHeight() * 2 / 3);
        Log.d(tag, lv_layout.getMeasuredWidth() + "/ 너비 /" + lv_layout.getMeasuredHeight());
        Log.d(tag, location[0] + "/ 위치 /" + location[1]);

        Toast.makeText(this,"READY",Toast.LENGTH_SHORT);
    }

    private void setElements() {
        mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);

        lv_layout = (LinearLayout) findViewById(R.id.map_view_layout);
        iv_centerMarker = (ImageView) findViewById(R.id.map_view_center_marker);
        bt_setPosition = (BootstrapButton) findViewById(R.id.map_view_btn_set_position);

        presenter = new MapViewPresenter(this);

        bt_setPosition.setOnClickListener(this);
    }


    @UiThread
    @Override
    public void onMapReady(@NonNull final NaverMap naverMap) {
        this.naverMap = naverMap;

        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                Log.d(tag, latLng.latitude + " / " + latLng.longitude);
                LatLng[] latLngs = naverMap.getContentRegion();

                double lat = Math.round(((latLngs[0].latitude + latLngs[1].latitude) / 2) * 100000) / 100000.0;
                double longi = Math.round(((latLngs[1].longitude + latLngs[2].longitude) / 2) * 100000) / 100000.0;

                Log.d(tag, lat + " / " + longi);
            }
        });
    }

    //마커가 가리키는 좌표값을 보내줌.
    private double[] getLocation() {
        double[] location = new double[2];
        LatLng[] latLngs = naverMap.getContentRegion();
        location[0] = Math.round(((latLngs[0].latitude + latLngs[1].latitude) / 2) * 100000) / 100000.0;
        location[1] = Math.round(((latLngs[1].longitude + latLngs[2].longitude) / 2) * 100000) / 100000.0;
        return location;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.map_view_btn_set_position:
                presenter.clickSetLocation(getLocation());
                break;
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
