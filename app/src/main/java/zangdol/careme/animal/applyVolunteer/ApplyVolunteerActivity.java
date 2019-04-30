package zangdol.careme.animal.applyVolunteer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ForegroundColorSpan;
import android.util.Log;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import zangdol.careme.R;

public class ApplyVolunteerActivity extends AppCompatActivity implements OnMonthChangedListener, ApplyVolunteerContract.View {

    private ApplyVolunteerPresenter presenter;

    private MaterialCalendarView materialCalendarView;

    private ApplyVolunteerActivity me;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_volunteer);
        presenter = new ApplyVolunteerPresenter(this);
        me = this;
        setElements();


        String[] dates = getSearchDate(getCurYear(), getCurMonth());
        presenter.getSchedule(dates[0], dates[1]);

    }

    private void setElements() {
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.apply_volunteer_calendar);
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY)
                .setMinimumDate(CalendarDay.from(getCurYear(), getCurMonth() - 1, 1))
                .setMaximumDate(CalendarDay.from(getCurYear() + 1, getCurMonth() - 1, 31))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();

        materialCalendarView.setSelectionColor(Color.BLACK);
        materialCalendarView.setOnMonthChangedListener(this);
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        Log.d("Month", date.getYear() + "년" + date.getMonth() + "월");
        String[] searchDates = getSearchDate(date.getYear(), date.getMonth() + 1);

        presenter.getSchedule(searchDates[0], searchDates[1]);
    }

    @Override
    public void setSchedule(final HashSet<CalendarDay> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                materialCalendarView.addDecorators(new ScheduleDecorator(list, getApplicationContext()));
                materialCalendarView.invalidateDecorators();
            }
        });
    }

    private int getCurMonth() {
        Date curDate = Calendar.getInstance().getTime();
        return curDate.getMonth() + 1;
    }

    private int getCurYear() {
        Date curDate = Calendar.getInstance().getTime();
        return curDate.getYear() - 100 + 2000;
    }

    private String[] getSearchDate(int year, int month) {
        String[] dates = new String[2];

        if (month == 12) {
            dates[0] = year + "-12-1";
            dates[1] = (year + 1) + "-1-1";
        } else {
            dates[0] = year + "-" + month + "-1";
            dates[1] = year + "-" + (month + 1) + "-1";
        }

        return dates;

    }


    private class ScheduleDecorator implements DayViewDecorator {

        private HashSet<CalendarDay> schedules;
        private Drawable drawable;

        public ScheduleDecorator(HashSet<CalendarDay> schedules, Context context) {
            this.schedules = schedules;
            drawable = context.getDrawable(R.drawable.no);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            //버그인지 모르겠으나 0,1,2,3,4,5,6,7,8,9 순서대로 월을 표시하는 것으로 판단.
            CalendarDay realDay = new CalendarDay(day.getYear(), (day.getMonth() + 1), day.getDay());
            if (schedules.contains(realDay)) {
                return true;
            }
            return false;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.GRAY));
            view.setBackgroundDrawable(drawable);
            view.setDaysDisabled(true);
        }
    }
}
