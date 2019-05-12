package zangdol.careme.map;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import zangdol.careme.restapi.naverMap.GetAddress;

import static android.app.Activity.RESULT_OK;

public class MapViewPresenter implements MapViewContract.Presenter, GetAddress.OnGetAddressListener {
    private MapViewContract.View view;
    private double[] location;

    @Override
    public void onGetAddress(final String addr) {
        Log.d("TESTPresenter",addr);
        view.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("address",addr);
                resultIntent.putExtra("latitude",location[0]);
                resultIntent.putExtra("longitude",location[1]);
                view.getActivity().setResult(RESULT_OK,resultIntent);
                view.getActivity().finish();
            }
        });

    }


    public enum ReturnActivity {
        DOGENROLLMENT
    }

    @Override
    public void clickSetLocation(double[] location) {
        this.location = location;
        new GetAddress("" + location[0], "" + location[1], this);
    }


    public MapViewPresenter(MapViewContract.View view) {
        this.view = view;
    }
}
