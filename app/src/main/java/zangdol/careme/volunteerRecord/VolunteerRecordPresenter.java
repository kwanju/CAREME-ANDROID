package zangdol.careme.volunteerRecord;

import java.util.ArrayList;

import zangdol.careme.model.VolunteerRecord;
import zangdol.careme.restapi.GetUserSchedule;
import zangdol.careme.util.SaveSharedPreference;

public class VolunteerRecordPresenter implements VolunteerRecordContract.Presenter, GetUserSchedule.OnUserScheduleListener {
    private VolunteerRecordActivity activity;

    public VolunteerRecordPresenter(VolunteerRecordActivity activity) {
        this.activity = activity;
    }

    @Override
    public void getSchedule() {
        //로그인 검증 필요
        String userIdx = SaveSharedPreference.getIdx();

        new GetUserSchedule(userIdx,this);
    }

    @Override
    public void onUserSchedule(ArrayList<VolunteerRecord> volunteerRecords) {
        activity.setSchedule(new VolunteerRecordAdapter(volunteerRecords,activity));
    }
}
