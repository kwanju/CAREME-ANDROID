package zangdol.careme.searchFilterDogs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.DogInfoFiltered;

public class DogFilterAdapter extends ArrayAdapter<DogInfoFiltered>
{

    private ArrayList<DogInfoFiltered> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_type;
        TextView tv_date;
        TextView tv_region;
        TextView tv_place;
        ImageView dogImg;
    }

    public DogFilterAdapter(ArrayList<DogInfoFiltered> data, Context context) {
        super(context, R.layout.filtered_dogs_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        DogInfoFiltered dogData = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //TextView tv_type;
            //TextView tv_date;
            //TextView tv_region;
           // TextView tv_place;
           // ImageView dogImg;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.filtered_dogs_listitem, parent, false);
            viewHolder.tv_type = (TextView) convertView.findViewById(R.id.textView25);
            viewHolder.tv_date = (TextView) convertView.findViewById(R.id.textView27);
            viewHolder.tv_region = (TextView) convertView.findViewById(R.id.textView29);
            viewHolder.tv_place = (TextView) convertView.findViewById(R.id.textView31);
            viewHolder.dogImg = (ImageView) convertView.findViewById(R.id.imageView3);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //TextView tv_type;
        //TextView tv_date;
        //TextView tv_region;
        // TextView tv_place;
        // ImageView dogImg;
        //////////////////

        viewHolder.tv_type.setText(dogData.getType());
        viewHolder.tv_date.setText(dogData.getDate());
        viewHolder.tv_region.setText(dogData.getRegion());
        viewHolder.tv_place.setText(dogData.getPlace());
        viewHolder.dogImg.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }


}
