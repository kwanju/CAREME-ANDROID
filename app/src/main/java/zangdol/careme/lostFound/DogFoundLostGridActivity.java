package zangdol.careme.lostFound;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.DogReport;

public class DogFoundLostGridActivity extends AppCompatActivity
{
    private GridView gv;
    private DogFoundLostAdapter adapter;
    private ArrayList<DogReport> dogReports;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_lost_list);
        setTitle( "Dog Report" );

        gv = (GridView)findViewById( R.id.gridview_lost_found );
        dogReports = new ArrayList<>();
        adapter = new DogFoundLostAdapter(this, dogReports);
        gv.setAdapter( adapter );

//////////////////////////////실종,목격,보호되는 유기견들을 불러와서 아래에 add한다.        //
        dogReports.add( new DogReport( "실종", "치와와", "수컷/하얀색/2살/5kg",
                                                                                    "2019-05-15","아주대 근처", null  ));
        dogReports.add( new DogReport( "목격", "진돗개", "암컷/하얀색/3살/7kg",
                                                                                    "2019-05-15","낙성대 근처", null  ));
        dogReports.add( new DogReport( "보호", "도사견", "암컷/하얀색/3살/4kg",
                                                                                    "2019-05-13","경기 수원시 영통구 덕영대로 ", null  ));
        dogReports.add( new DogReport( "보호", "불독", "암컷/하얀색/4살/3kg",
                                                                                    "2019-05-12","노량진동 근처", null  ));
        dogReports.add( new DogReport( "실종", "삽살개", "수컷/하얀색/2살/5kg",
                                                                            "2019-05-11","아주대앞 커피나무", null  ));

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               DogReport dogReport = dogReports.get(position);
////////////////////////////dogReport에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
                Toast.makeText( getApplicationContext(), dogReport.getStatus()
                       + " " +dogReport.getType() + " "+ dogReport.getDescription()
            +" "+ dogReport.getDate() +" " + dogReport.getPlace(), Toast.LENGTH_LONG).show();
////////////////////////////////////////////intent써서 DogInformationActivity를 연다////////////////////////////////////////////////////////////////////
                startActivity(new Intent( DogFoundLostGridActivity.this, DogInformationActivity.class));
            }
        });
    }
}