package zangdol.careme.bulletinBoardDiscoverFind;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import zangdol.careme.R;
import zangdol.careme.registerDiscoverFind.RegisterDiscoverFindActivity;

public class BulletinBoardDiscoverFindActivity extends AppCompatActivity implements BulletinBoardDiscoverFindContract.View, View.OnClickListener {
    private GridView gv;

    private BulletinBoardDiscoverFindContract.Presenter presenter;
    private FloatingActionButton fab_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulletin_board_discover_find);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_discover_find);

        setItem();
        presenter = new BulletinBoardDiscoverFindPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
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
                Intent intent = new Intent(this, RegisterDiscoverFindActivity.class);
                startActivity(intent);
                break;
        }
    }
}