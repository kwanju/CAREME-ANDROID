package zangdol.careme.lostFound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zangdol.careme.R;
import zangdol.careme.model.DogReport;

public class GridItem extends LinearLayout
{
    private ImageView doggyImg;
    private TextView tv_status;
    private TextView tv_type;
    private TextView tv_description;
    private TextView tv_date_found_lost;
    private TextView tv_place;

    public GridItem( Context context )
    {
        super( context );
        init( context);
    }
    public void init( Context context )
    {
        View view = LayoutInflater.from(context).inflate(R.layout.gridview_item_abandoned, this);
        doggyImg = (ImageView)findViewById( R.id.dogImg_found_lost );
        tv_status = (TextView)findViewById( R.id.tv_status );
        tv_type = (TextView)findViewById( R.id.dogType );
        tv_description = (TextView)findViewById( R.id.dogInfo );
        tv_date_found_lost = (TextView)findViewById( R.id.tv_dateFound );
        tv_place = (TextView)findViewById( R.id.tv_placeFound );
    }
    public void setData( DogReport one, boolean visible )
    {
        tv_status.setText( one.getStatus() );
        tv_type.setText( one.getType() );
        tv_description.setText( one.getDescription() );
        tv_date_found_lost.setText( one.getDate() );
        tv_place.setText( one.getPlace() );

        if( tv_status.getText().equals("실종") )
            tv_status.setBackgroundColor( 0x90FFA500 );
        else if (tv_status.getText().equals("목격"))
            tv_status.setBackgroundColor( 0x90FFFF00 );
        else
            tv_status.setBackgroundColor( 0x90009900 );
/////////////////////여기에 doggyImg 이미지뷰에 URL 사진(클래스 DogReport에 imgURL)을 짱박는다.////////////////////////////////////////////////
    }
}
