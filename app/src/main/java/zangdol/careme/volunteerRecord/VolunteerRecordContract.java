package zangdol.careme.volunteerRecord;

import java.util.ArrayList;

import zangdol.careme.model.VolunteerRecord;

public class VolunteerRecordContract {
    public interface View{
        void setSchedule(VolunteerRecordAdapter adapter);
    }
    public interface Presenter{
        void getSchedule();
    }
}
