package zangdol.careme.registerDiscover;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;

import zangdol.careme.R;
import zangdol.careme.map.MapViewActivity;

public class RegisterDiscoverActivity extends AppCompatActivity implements RegisterDiscoverContract.View, View.OnClickListener, OnDateSetListener {

    private RegisterDiscoverContract.Presenter presenter;

    private Button bt_register;
    private ImageView dogImage;

    private Bitmap dogImageUploaded;

    private TextView tv_address;
    private TextView tv_datetime;
    private TextView tv_des;
    private TextView tv_sex;
    private TextView tv_species;

    private TimePickerDialog mDialogAll;

    private final static int IMAGE = 0;
    private final static int ADDRESS = 1;

    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_discover);

        presenter = new RegisterDiscoverPresenter(this);

        setItem();
        setDateTimePicker();

    }

    public void setItem() {
        bt_register = (Button) findViewById(R.id.register_discover_btn_register);
        dogImage = (ImageView) findViewById(R.id.register_discover_dogImage);

        tv_address = (TextView) findViewById(R.id.register_discover_address);
        tv_datetime = (TextView) findViewById(R.id.register_discover_datetime);
        tv_des = (TextView) findViewById(R.id.register_discover_description);
        tv_sex = (TextView) findViewById(R.id.register_discover_sex);
        tv_species = (TextView) findViewById(R.id.register_discover_species_code);

        dogImage.setOnClickListener(this);
        tv_address.setOnClickListener(this);
        tv_datetime.setOnClickListener(this);
        bt_register.setOnClickListener(this);
    }

    private void setDateTimePicker() {
        long years = 1L * 365 * 1000 * 60 * 60 * 24L;

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        mDialogAll = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("취소")
                .setSureStringId("선택")
                .setTitleStringId("날짜 / 시간 선택")
                .setYearText("년")
                .setMonthText("월")
                .setDayText("일")
                .setHourText("시")
                .setMinuteText("분")
                .setCyclic(false)
                //.setMinMillseconds(System.currentTimeMillis())
                .setMaxMillseconds(System.currentTimeMillis() + years)
                .setCurrentMillseconds(System.currentTimeMillis())
                .setType(Type.ALL)
                .setWheelItemTextSize(12)
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImageUri = data.getData();
                        // 선택한 이미지에서 비트맵 생성
                        InputStream in = getContentResolver().openInputStream(data.getData());
                        Bitmap img = BitmapFactory.decodeStream(in);
                        in.close();
                        // 이미지 표시
                        dogImage.setImageBitmap(img);
                        dogImageUploaded = img;

                        presenter.setImageUri(selectedImageUri);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ADDRESS:
                if (resultCode == RESULT_OK) {
                    String addr = data.getStringExtra("address");
                    presenter.setData("address", addr);
                    presenter.setData("latitude", "" + data.getDoubleExtra("latitude", 0));
                    presenter.setData("longitude", "" + data.getDoubleExtra("longitude", 0));
                    tv_address.setText(addr);
                }
                break;
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_discover_dogImage: // 이미지 등록 사진을 눌렀을 때
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE);
                break;
            case R.id.register_discover_address: // 주소를 눌렀을 때
                Intent mapIntent = new Intent(this, MapViewActivity.class);
                startActivityForResult(mapIntent, ADDRESS);
                break;
            case R.id.register_discover_datetime: // 날짜선택을 눌렀을 때
                Log.d("TEST", "IN");
                mDialogAll.show(getSupportFragmentManager(), "all");
                break;
            case R.id.register_discover_btn_register: // 등록버튼을 눌렀을 때.
                presenter.setData("description", tv_des.getText().toString());
                presenter.setData("sex", tv_sex.getText().toString());
                presenter.setData("species_code", tv_species.getText().toString());
                presenter.register();
                break;
        }
    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        Date selectedDate = new Date(millseconds);
        tv_datetime.setText(simpleDateFormat.format(selectedDate));
        presenter.setData("datetime", simpleDateFormat.format(selectedDate));
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
