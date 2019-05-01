package zangdol.careme.restapi;

import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import zangdol.careme.Config;

public class GetAnimalSchedule implements RestUtil.OnRestApiListener {

    private OnAnimalScheduleListener listener;

    private RestUtil restUtil;

    private final String URL = Config.SERVERIP + "android/shelter/animal/json/getAnimalSchedule";

    public interface OnAnimalScheduleListener {
        void onAnimalSchedule(HashSet<CalendarDay> list);
    }

    public GetAnimalSchedule(String idx, String start_date, String end_date, OnAnimalScheduleListener listener) {
        restUtil = new RestUtil();
        this.listener = listener;

        List<NameValuePair> params = new ArrayList<NameValuePair>(3);

        params.add(new BasicNameValuePair("animal_idx", idx));
        params.add(new BasicNameValuePair("start_date", start_date));
        params.add(new BasicNameValuePair("end_date", end_date));

        restUtil.request(URL, params, this);
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
                Log.d("GetAnimalScheduler",""+Integer.parseInt(date.substring(0, 4))+ Integer.parseInt(date.substring(5, 7))+ Integer.parseInt(date.substring(8, 10)));
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
