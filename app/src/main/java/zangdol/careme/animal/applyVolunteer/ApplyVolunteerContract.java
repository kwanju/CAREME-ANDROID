package zangdol.careme.animal.applyVolunteer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashMap;
import java.util.HashSet;

public class ApplyVolunteerContract {
    public interface View {
        void setSchedule(final HashSet<CalendarDay> list);
        void setVolunteerInfo(HashMap<String,String> volunteerInfo);
    }

    public interface Presenter {
        void getSchedule(String start_date, String end_date);
        void getVolunteerShelterInfo();
    }
}
