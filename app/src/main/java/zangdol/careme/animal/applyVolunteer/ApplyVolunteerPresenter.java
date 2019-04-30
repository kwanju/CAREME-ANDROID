package zangdol.careme.animal.applyVolunteer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import zangdol.careme.restapi.GetAnimalSchedule;

public class ApplyVolunteerPresenter implements ApplyVolunteerContract.Presenter, GetAnimalSchedule.OnAnimalScheduleListener {
    private ApplyVolunteerActivity activity;

    private GetAnimalSchedule schedule;
    private String animal_idx;

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
    public void onAnimalSchedule(HashSet<CalendarDay> list) {
        activity.setSchedule(list);
    }
}
