package zangdol.careme.bulletinBoardDiscoverFind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.Discover;
import zangdol.careme.model.DiscoverFind;
import zangdol.careme.util.ConvertManager;
import zangdol.careme.util.NullChecker;

public class BulletinBoardDiscoverFindItem extends LinearLayout {
    private ImageView doggyImg;
    private TextView tv_status;
    private TextView tv_type;
    private TextView tv_sex;
    private TextView tv_date_found_lost;
    private TextView tv_place;

    public BulletinBoardDiscoverFindItem(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item_abandoned, this);
        doggyImg = (ImageView) findViewById(R.id.dogImg_found_lost);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_type = (TextView) findViewById(R.id.dogType);
        tv_sex = (TextView) findViewById(R.id.dogInfo);
        tv_date_found_lost = (TextView) findViewById(R.id.tv_dateFound);
        tv_place = (TextView) findViewById(R.id.tv_placeFound);
    }

    public void setData(DiscoverFind discoverFind) {
        tv_type.setText(ConvertManager.getSpecies(discoverFind.getSpeciesCode()));
        tv_sex.setText(discoverFind.getAnimalSex().equals("")?"성별 입력안됨":discoverFind.getAnimalSex().equals("w")?"여자":"남자");
        tv_date_found_lost.setText(ConvertManager.date(discoverFind.getEventDateTime(), ConvertManager.DATETIME));
        tv_place.setText(discoverFind.getEventSpot());

        setStatus(discoverFind);
        NullChecker.image(discoverFind.getUrl_picture(), doggyImg);


    }

    private void setStatus(DiscoverFind discoverFind) {
        if (Discover.class.isInstance(discoverFind)) {
            tv_status.setText("발견했어요");
            tv_status.setBackgroundColor(0x6A5ACD00);
        } else {
            tv_status.setText("찾아요");
            tv_status.setBackgroundColor(0x90FFA500);
        }
    }
}
