package zangdol.careme.bulletinBoard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.BulletinData;

public class BulletinListAdapter extends ArrayAdapter<BulletinData>
{
    private ArrayList<BulletinData> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_title;
        TextView tv_id;
        TextView tv_time;
        TextView tv_hits;
        TextView tv_noOfComments;

    }

    public BulletinListAdapter(ArrayList<BulletinData> data, Context context) {
        super(context, R.layout.bulletin_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        BulletinData bulletinData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //TextView tv_title;
            //TextView tv_id;
            //TextView tv_time;
            //TextView tv_hits;
            //TextView tv_noOfComments;
            //ImageView hitImg;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.bulletin_listitem, parent, false);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.tv_id = (TextView) convertView.findViewById(R.id.id);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.time);
            viewHolder.tv_hits = (TextView) convertView.findViewById(R.id.numberOfHits);
            viewHolder.tv_noOfComments = (TextView) convertView.findViewById(R.id.numberOfComments);





            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_title;
        //TextView tv_id;
        //TextView tv_time;
        //TextView tv_hits;
        //TextView tv_noOfComments;
        //ImageView hitImg;
        //////////////////

        viewHolder.tv_title.setText(bulletinData.getTitle());
        viewHolder.tv_id.setText(bulletinData.getId());
        viewHolder.tv_time.setText(bulletinData.getTime());
        viewHolder.tv_hits.setText(bulletinData.getHit());
        viewHolder.tv_noOfComments.setText(bulletinData.getNoOfComments());

        // Return the completed view to render on screen
        return convertView;
    }
}
