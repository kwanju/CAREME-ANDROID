package zangdol.careme.animal.applyVolunteer;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashSet;

public class ApplyVolunteerContract {
    public interface View {
        void setSchedule(final HashSet<CalendarDay> list);
    }

    public interface Presenter {
        void getSchedule(String start_date, String end_date);

    }
}
