package zangdol.careme.bulletinBoard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.BulletinData;

public class BulletinActivity extends AppCompatActivity
{

    private ArrayList<BulletinData> bulletinDatas;
    private ListView contentListView;
    private BulletinListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin);

        contentListView=(ListView)findViewById(R.id.contentList);
        bulletinDatas = new ArrayList<>();
        adapter= new BulletinListAdapter( bulletinDatas,getApplicationContext());
//////////서버에 저장된 게시글들을 불러온다. 필요한 정보는 제목, 아이디, 시간, 조회수, 댓글수이다./////////////////////////////////
        bulletinDatas.add(new BulletinData("꽁꽁이를 찾아요", "아나씨브럴", "19:17", "221", "0"));
        bulletinDatas.add(new BulletinData("우리 예삐를 찾았습니다.", "아항아항아항", "19:17", "22", "0"));
        bulletinDatas.add(new BulletinData("우리 댕댕이를 정말 간절히 찾습니다.", "댕댕이주인", "19:16", "18", "1"));
        bulletinDatas.add(new BulletinData("우리 강아지를 찾아요", "급하게찾기위해가입했어요", "19:16", "21", "2"));
        bulletinDatas.add(new BulletinData("강아지를 잃어버렸어요", "쏘데스카", "19:10", "7", "5"));

        contentListView.setAdapter(adapter);
        contentListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                BulletinData bulletinData = bulletinDatas.get(position);
////////////////////////////bulletinData에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
              //  Toast.makeText( getApplicationContext(), bulletinData.getShelterName()
             //           +  " " +shelterData.getAddress()
             //           + " "+ shelterData.getPhone(),
              //                                                          Toast.LENGTH_LONG).show();
///////////////////////////////////////////// 해당 게시판게시글을 띄워준다.////////////////////////////////////////////////////////

            }
        });


    }
}