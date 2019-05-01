package zangdol.careme.restapi;

import org.json.JSONObject;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class ApplySchedule implements RestUtil.OnRestApiListener {

    private final String URL = Config.SERVERIP + "android/shelter/animal/action/registerSchedule";
    private OnApplyScheduleListener listener;

    public interface OnApplyScheduleListener {
        void onApplySchedule();
    }

    public ApplySchedule(final String date, final String animalIdx, final String userIdx, OnApplyScheduleListener listener) {
        this.listener = listener;
        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 3;
            }

            @Override
            public void setParams() {
                addParam("date", date);
                addParam("animal_idx", animalIdx);
                addParam("user_idx", userIdx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onApplySchedule();
    }
}
