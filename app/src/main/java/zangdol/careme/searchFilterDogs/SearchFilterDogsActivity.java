package zangdol.careme.searchFilterDogs;

import android.app.DatePickerDialog;
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

import zangdol.careme.R;
import zangdol.careme.model.DogInfoFiltered;

public class SearchFilterDogsActivity extends AppCompatActivity
{
    private Button bt_start;
    private Button bt_end;
    private CheckBox cb_period_doncare;

    private EditText et_dogtype;
    private CheckBox cb_type_doncare;

    private Spinner spinner_region;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    private RadioButton rb_male;
    private RadioButton rb_female;
    private RadioButton rb_sex_doncare;

    private EditText et_age_start;
    private EditText et_age_end;
    private CheckBox cb_age_doncare;

    private EditText et_weight_start;
    private EditText et_weight_end;
    private CheckBox cb_weight_doncare;

    private Button bt_filter;

    private ListView lv_filtered_dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_dogs_abandoned);

        setItem();

    }
    public void setItem()
    {
        bt_start = (Button)findViewById( R.id.period_start );
        bt_end = (Button)findViewById( R.id.period_end );
        cb_period_doncare = (CheckBox)findViewById(R.id.checkBox1);

        et_dogtype = (EditText)findViewById(R.id.et_dog_typecode);
        cb_type_doncare = (CheckBox)findViewById(R.id.type_checkbox);

        spinner_region = (Spinner)findViewById(R.id.spinner_region);
        arrayList = new ArrayList<>();
        arrayList.add( "전체" );
        arrayList.add( "서울" ); arrayList.add( "경기" ); arrayList.add( "인천" ); arrayList.add( "강원" );
        arrayList.add( "충북" ); arrayList.add( "충남" ); arrayList.add( "대전" ); arrayList.add( "전북" );
        arrayList.add( "전남" ); arrayList.add( "광주" ); arrayList.add( "경북" ); arrayList.add( "경남" );
        arrayList.add( "대구" ); arrayList.add( "울산" ); arrayList.add( "부산" ); arrayList.add( "제주특별자치도" );
        arrayList.add( "세종특별자치시" );
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner_region.setAdapter( arrayAdapter );

        rb_male = (RadioButton)findViewById( R.id.radioButton10 );
        rb_female = (RadioButton)findViewById( R.id.radioButton11 );
        rb_sex_doncare = (RadioButton)findViewById( R.id.radioButton12 );

        et_age_start = (EditText)findViewById( R.id.et_start_age);
        et_age_end = (EditText)findViewById( R.id.et_end_age);
        cb_age_doncare = (CheckBox)findViewById( R.id.age_checkbox );

        et_weight_start = (EditText)findViewById(R.id.editText6);
        et_weight_end = (EditText)findViewById(R.id.editText7);
        cb_weight_doncare = (CheckBox)findViewById( R.id.weight_checkbox );

        bt_filter = (Button)findViewById(R.id.filterButton);

        lv_filtered_dogs = (ListView)findViewById(R.id.doggy_abandoned_filteredlistview);

    }

/////////////////////////////////////// 이 사이에 검색조건을 모두 입력받는다. 서버에 보내 강아지 리스트를 받아온다.//////////////////////////////////////
    public void onButtonFilterClick(View view)
    {
        String date_start = null;   // 시작일
        String date_end = null;   // 종료일
        boolean date_care = true;  // 날짜 상관이 있는지
        String dogType = null;  //견종
        boolean type_care = true;  //견종이 상관 있는지
        String region = null;  //지역(시도 단위)
        String sex = null;  //성별
        int age_start;   //시작 나이
        int age_end;  //종료 나이
        boolean age_care = true;  //나이가 상관 있는지
        int weight_start;  //시작 몸무게
        int weight_end;  //종료 몸무기
        boolean weight_care = true;  //몸무게 상관 있는지


        date_start = (String) bt_start.getText();
        date_end = (String) bt_end.getText();
        date_care = !(cb_period_doncare.isChecked());

        dogType = (String) et_dogtype.getText().toString();
        type_care = !(cb_type_doncare.isChecked());

        region = spinner_region.getSelectedItem().toString();

        if(rb_male.isChecked())
            sex = "male";
        else if(rb_female.isChecked())
            sex = "female";
        else
            sex = null;

        age_start = Integer.parseInt(et_age_start.getText().toString());
        age_end = Integer.parseInt(et_age_end.getText().toString());
        age_care = !( cb_age_doncare.isChecked());

        weight_start = Integer.parseInt(et_weight_start.getText().toString());
        weight_end = Integer.parseInt(et_weight_end.getText().toString());
        weight_care = !( cb_weight_doncare.isChecked());

//////////////////////////////////////////////////이 변수들을 서버에 보내 해당되는 유기견들 리스트를 받아온다.

        ArrayList<DogInfoFiltered> dogDatas;
        dogDatas = new ArrayList<>();
        DogFilterAdapter adapter;
        adapter= new DogFilterAdapter( dogDatas,getApplicationContext());
        lv_filtered_dogs.setAdapter(adapter);
        /////////여기서는 임의의 리스트를 쓰겠다.
        dogDatas.add(new DogInfoFiltered("말티즈", "2019-05-21", "경남 창원", "의창구 명서동 11507번지 옥상"));
        dogDatas.add(new DogInfoFiltered("치와와", "2019-05-20", "경남 창원", "의창구 명서동 11507번지 옥상"));
        dogDatas.add(new DogInfoFiltered("믹스견", "2019-05-19", "경남 창원", "의창구 명서동 11507번지 옥상"));


    }
////////////////////////////////////////이 사이에 검색조건을 모두 입력받는다. 서버에 보내 강아지 리스트를 받아온다. /////////////////////////////////////////////
    public void onButtonClick1( View view )
    {
        DatePickerDialog dialog = new DatePickerDialog(this, listener1, 2019, 5, 31);

        dialog.show();
    }
    public void onButtonClick2( View view )
    {
        DatePickerDialog dialog = new DatePickerDialog(this, listener2, 2019, 5, 31);

        dialog.show();
    }
    private DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            monthOfYear = monthOfYear +1;

            bt_start.setText( year + "-" + monthOfYear +"-" + dayOfMonth );
        }

    };
    private DatePickerDialog.OnDateSetListener listener2 = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
        {
            monthOfYear = monthOfYear +1;

            bt_end.setText( year + "-" + monthOfYear +"-" + dayOfMonth );
        }

    };


}
