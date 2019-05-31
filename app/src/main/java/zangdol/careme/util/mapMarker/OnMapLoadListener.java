package zangdol.careme.util.mapMarker;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.overlay.Marker;

import zangdol.careme.util.mapMarker.ShowMapWithMarker;

public abstract class OnMapLoadListener {
    abstract public ShowMapWithMarker getShowMapWithMarker();
    abstract public String getLatitude();

    abstract public String getLongitude();

    public void loadMarker() {
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(Double.parseDouble(getLatitude()), Double.parseDouble(getLongitude())));
        getShowMapWithMarker().getNaverMap().moveCamera(cameraUpdate);

        Marker marker = new Marker();
        marker.setPosition(new LatLng(Double.parseDouble(getLatitude()), Double.parseDouble(getLongitude())));
        marker.setMap(getShowMapWithMarker().getNaverMap());
    }
}
