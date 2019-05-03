package zangdol.careme.restapi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import zangdol.careme.Config;
import zangdol.careme.model.VolunteerRecord;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetUserSchedule implements RestUtil.OnRestApiListener {

    private OnUserScheduleListener listener;

    public interface OnUserScheduleListener {
        void onUserSchedule(ArrayList<VolunteerRecord> volunteerRecords);
    }

    public GetUserSchedule(final String idx, OnUserScheduleListener listener) {
        this.listener = listener;
        String URL = Config.SERVERIP + "android/user/json/getUserSchedule";
        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 1;
            }

            @Override
            public void setParams() {
                addParam("idx", idx);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onUserSchedule(json2volunteer(result));
    }

    private ArrayList<VolunteerRecord> json2volunteer(JSONObject result) {
        ArrayList<VolunteerRecord> volunteerRecords = new ArrayList<>();

        try {
            JSONArray volunteers = result.getJSONArray("list");
            for (int i = 0; i < volunteers.length(); i++) {
                JSONObject volunteer = volunteers.getJSONObject(i);

                VolunteerRecord vr = new VolunteerRecord(
                        volunteer.getString("shelterName"),
                        volunteer.getString("animalName"),
                        volunteer.getString("date"),
                        volunteer.getString("permit"),
                        volunteer.getString("url_picture"));
                volunteerRecords.add(vr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return volunteerRecords;
    }
}
