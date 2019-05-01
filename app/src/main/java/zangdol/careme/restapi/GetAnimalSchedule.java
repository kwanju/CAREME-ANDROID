package zangdol.careme.restapi;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;

import zangdol.careme.Config;
import zangdol.careme.restapi.core.Parameters;
import zangdol.careme.restapi.core.RestFactory;
import zangdol.careme.restapi.core.RestUtil;

public class GetAnimalSchedule implements RestUtil.OnRestApiListener {

    private OnAnimalScheduleListener listener;

    private final String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalSchedule";

    public interface OnAnimalScheduleListener {
        void onAnimalSchedule(HashSet<CalendarDay> list);
    }

    public GetAnimalSchedule(final String idx, final String start_date, final String end_date, OnAnimalScheduleListener listener) {
        this.listener = listener;

        RestFactory.getInstance().request(URL, this, new Parameters() {
            @Override
            public int getNumParams() {
                return 3;
            }

            @Override
            public void setParams() {

                addParam("animal_idx", idx);
                addParam("start_date", start_date);
                addParam("end_date", end_date);
            }
        });
    }

    @Override
    public void OnResult(JSONObject result) {
        listener.onAnimalSchedule(json2hashCalendarDay(result));
    }

    private HashSet<CalendarDay> json2hashCalendarDay(JSONObject result) {
        HashSet<CalendarDay> list = new HashSet<>();

        try {
            JSONArray schedules = result.getJSONArray("schedule");

            for (int i = 0; i < schedules.length(); i++) {
                JSONObject schedule = schedules.getJSONObject(i);
                String date = schedule.getString("date");
                Log.d("GetAnimalScheduler", "" + Integer.parseInt(date.substring(0, 4)) + Integer.parseInt(date.substring(5, 7)) + Integer.parseInt(date.substring(8, 10)));
                list.add(new CalendarDay(
                        Integer.parseInt(date.substring(0, 4)),
                        Integer.parseInt(date.substring(5, 7)),
                        Integer.parseInt(date.substring(8, 10))));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
