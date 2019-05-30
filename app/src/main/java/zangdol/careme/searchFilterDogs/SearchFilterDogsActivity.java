package zangdol.careme.searchFilterDogs;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

import zangdol.careme.R;
import zangdol.careme.map.MapViewActivity;
import zangdol.careme.util.speciesCodeInputDialog.SpeciesCodeInputDialog;

public class SearchFilterDogsActivity extends AppCompatActivity implements SearchFilterDogsContract.View, View.OnClickListener {
    private SearchFilterDogsContract.Presenter presenter;

    private final static int ADDRESS = 0;

    private Spinner spinner_distance;
    private Button bt_start;
    private Button bt_end;

    private EditText et_dogTypecode;
    private EditText et_dogtype;

    private EditText et_address;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    private RadioButton rb_male;
    private RadioButton rb_female;


    private Button bt_filter;

    private CustomListView lv_filtered_dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_dogs_abandoned);
        presenter = new SearchFilterDogsPresenter(this);
        setItem();

    }

    public void setItem() {
        bt_start = (Button) findViewById(R.id.period_start);
        bt_end = (Button) findViewById(R.id.period_end);

        et_dogTypecode = (EditText) findViewById(R.id.fd_et_dog_typecode);
        et_dogtype = (EditText) findViewById(R.id.et_dog_typecode);

        spinner_distance = (Spinner) findViewById(R.id.spinner_distance);
        arrayList = new ArrayList<>();
        arrayList.add("1km 이내");
        arrayList.add("5km 이내");
        arrayList.add("10km 이내");
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner_distance.setAdapter(arrayAdapter);

        rb_male = (RadioButton) findViewById(R.id.radioButton10);
        rb_female = (RadioButton) findViewById(R.id.radioButton11);


        bt_filter = (Button) findViewById(R.id.filterButton);

        lv_filtered_dogs = (CustomListView) findViewById(R.id.doggy_abandoned_filteredlistview);

        et_address = (EditText) findViewById(R.id.fd_address);

        bt_filter.setOnClickListener(this);
        et_address.setOnClickListener(this);
        et_dogtype.setOnClickListener(this);

    }


    /////////////////////////////////////// 이 사이에 검색조건을 모두 입력받는다. 서버에 보내 강아지 리스트를 받아온다.//////////////////////////////////////
    public void onButtonFilterClick() {
        String date_start = null;   // 시작일
        String date_end = null;   // 종료일
        String distance = null;  //지역(시도 단위)
        String sex = null;  //성별

        date_start = (String) bt_start.getText();
        date_end = (String) bt_end.getText();

        switch (spinner_distance.getSelectedItemPosition()){
            case 0:
                distance="1";
                break;
            case 1:
                distance="5";
                break;
            case 2:
                distance="10";
                break;
        }
        if (rb_male.isChecked())
            sex = "m";
        else if (rb_female.isChecked())
            sex = "w";
        else
            sex = null;

        presenter.setData("start_date", date_start);
        presenter.setData("end_date", date_end);
        presenter.setData("species_code",et_dogTypecode.getText().toString());
        presenter.setData("sex",sex);
        presenter.setData("distance",distance);

        presenter.search();
    }

    ////////////////////////////////////////이 사이에 검색조건을 모두 입력받는다. 서버에 보내 강아지 리스트를 받아온다. /////////////////////////////////////////////
    public void onButtonClick1(View view) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, listener1, mYear, mMonth, mDay);

        dialog.show();
    }

    public void onButtonClick2(View view) {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, listener2, mYear, mMonth, mDay);

        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = monthOfYear + 1;
            bt_start.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }

    };
    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = monthOfYear + 1;
            bt_end.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
        }

    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filterButton:
                onButtonFilterClick();
                break;
            case R.id.fd_address:
                Intent mapIntent = new Intent(this, MapViewActivity.class);
                startActivityForResult(mapIntent, ADDRESS);
                break;
            case R.id.et_dog_typecode:
                new SpeciesCodeInputDialog(this, new SpeciesCodeInputDialog.OnSpeciesCodeSelectListener() {
                    @Override
                    public void onSpeciesCode(String[] result) {
                        et_dogTypecode.setText(result[0]);
                        et_dogtype.setText(result[1]);
                    }
                }).showDialog();
                break;
        }
    }

    @Override
    public void setAdapter(final DogFilterAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv_filtered_dogs.setAdapter(adapter);
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        switch (requestCode) {
            case ADDRESS:
                if (resultCode == RESULT_OK) {
                    String addr = data.getStringExtra("address");
                    presenter.setData("latitude", "" + data.getDoubleExtra("latitude", 0));
                    presenter.setData("longitude", "" + data.getDoubleExtra("longitude", 0));
                    et_address.setText(addr);
                }
                break;
        }

    }
}
