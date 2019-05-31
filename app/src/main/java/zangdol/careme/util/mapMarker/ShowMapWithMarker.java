package zangdol.careme.util.mapMarker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;

import zangdol.careme.R;


public class ShowMapWithMarker implements OnMapReadyCallback {
    private MapFragment mapFragment;
    private NaverMap naverMap;

    private AppCompatActivity callingActivity;

    private OnMapLoadListener listener;


    public ShowMapWithMarker(AppCompatActivity callingActivity) {
        this.callingActivity = callingActivity;

        mapFragment = (MapFragment) callingActivity.getSupportFragmentManager().findFragmentById(R.id.si_map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            callingActivity.getSupportFragmentManager().beginTransaction().add(R.id.si_map, mapFragment).commit();
        }
    }

    public void initialize(OnMapLoadListener listener) {
        this.listener = listener;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        listener.loadMarker();
    }

    public NaverMap getNaverMap() {
        return naverMap;
    }
}
