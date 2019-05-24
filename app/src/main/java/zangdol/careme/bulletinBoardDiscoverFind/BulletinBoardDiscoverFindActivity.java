package zangdol.careme.bulletinBoardDiscoverFind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import zangdol.careme.R;
import zangdol.careme.registerDiscover.RegisterDiscoverActivity;

public class BulletinBoardDiscoverFindActivity extends AppCompatActivity implements BulletinBoardDiscoverFindContract.View, View.OnClickListener {
    private GridView gv;

    private BulletinBoardDiscoverFindContract.Presenter presenter;
    private FloatingActionButton fab_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board_discover_find);
        setTitle("발견했어요 찾아요");

        setItem();
        presenter = new BulletinBoardDiscoverFindPresenter(this);
        presenter.getData();
/*
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DogReport dogReport = dogReports.get(position);
////////////////////////////dogReport에 있는 필요한 정보를 추출해서 서버에 보낸다.///////////////////////////////////
                Toast.makeText(getApplicationContext(), dogReport.getStatus()
                        + " " + dogReport.getType() + " " + dogReport.getDescription()
                        + " " + dogReport.getDate() + " " + dogReport.getPlace(), Toast.LENGTH_LONG).show();
////////////////////////////////////////////intent써서 DogInformationActivity를 연다////////////////////////////////////////////////////////////////////
                startActivity(new Intent(BulletinBoardDiscoverFindActivity.this, DiscoverFindActivity.class));
            }
        });*/
    }

    private void setItem() {
        gv = (GridView) findViewById(R.id.gridview_lost_found);
        fab_write = (FloatingActionButton) findViewById(R.id.bulletin_board_discover_find_write);

        fab_write.setOnClickListener(this);
    }

    @Override
    public void setAdapter(final BulletinBoardDiscoverFindAdapter adapter) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gv.setAdapter(adapter);
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bulletin_board_discover_find_write:
                Intent intent = new Intent(this, RegisterDiscoverActivity.class);
                startActivity(intent);
                break;
        }
    }
}