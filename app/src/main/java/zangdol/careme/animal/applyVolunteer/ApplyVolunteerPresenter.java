package zangdol.careme.animal.applyVolunteer;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import zangdol.careme.restapi.ApplySchedule;
import zangdol.careme.restapi.GetAnimalSchedule;
import zangdol.careme.restapi.GetVolunteerShelterInfo;
import zangdol.careme.util.AlarmManager;
import zangdol.careme.util.SaveSharedPreference;
import zangdol.careme.volunteerRecord.VolunteerRecordActivity;

public class ApplyVolunteerPresenter implements ApplyVolunteerContract.Presenter,ApplySchedule.OnApplyScheduleListener, GetAnimalSchedule.OnAnimalScheduleListener, GetVolunteerShelterInfo.OnVolunteerInfoListener {
    private ApplyVolunteerActivity activity;

    private GetAnimalSchedule schedule;
    private String animal_idx;

    private CalendarDay selectedDay = null;

    private List<String> scheduleCache; // 검색기록을 저장하기위한 리스트.

    public ApplyVolunteerPresenter(ApplyVolunteerActivity activity) {
        this.activity = activity;
        scheduleCache = new ArrayList<>();
        animal_idx = activity.getIntent().getStringExtra("animal_idx"); // animal_idx  가져옴.
    }

    @Override
    public void getSchedule(String start_date, String end_date) {
        if (scheduleCache.contains(start_date)) // 만약 이미 사전에 검색했다면.
            return;
        scheduleCache.add(start_date);
        schedule = new GetAnimalSchedule(animal_idx, start_date, end_date, this);
    }

    @Override
    public void getVolunteerShelterInfo() {
        new GetVolunteerShelterInfo(animal_idx, this);
    }

    @Override
    public void apply() {
        if(selectedDay==null){
            AlarmManager.alarm("날짜를 선택해주세요",activity);
            return;
        }
        if(!SaveSharedPreference.isLogin()){
            AlarmManager.alarm("로그인이 필요합니다.",activity);
            return;
        }
        String date = selectedDay.getYear()+"-"+(selectedDay.getMonth()+1)+"-"+selectedDay.getDay();
        String userIdx = SaveSharedPreference.getIdx();
        Log.d("TEST",date);
        new ApplySchedule(date,animal_idx,userIdx,this);
    }

    @Override
    public void setDate(CalendarDay day) {
        selectedDay = day;
    }

    @Override
    public void onAnimalSchedule(HashSet<CalendarDay> list) {
        activity.setSchedule(list);
    }


    @Override
    public void onVolunteerInfo(HashMap<String, String> volunteerInfo) {
        activity.setVolunteerInfo(volunteerInfo);
    }

    @Override
    public void onApplySchedule() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,"봉사 신청 성공",Toast.LENGTH_SHORT).show();

                AlarmManager.alarmYesNo("신청이 완료되었습니다. 신청목록을 확인하시겠습니까?",
                        activity, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.startActivity(new Intent(activity,VolunteerRecordActivity.class));
                            }
                        });
            }
        });
    }
}
