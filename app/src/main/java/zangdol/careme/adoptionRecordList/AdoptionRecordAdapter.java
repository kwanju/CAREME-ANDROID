package zangdol.careme.adoptionRecordList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zangdol.careme.R;
import zangdol.careme.model.AdoptionRecord;

public class AdoptionRecordAdapter extends ArrayAdapter<AdoptionRecord>
{
    private ArrayList<AdoptionRecord> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView tv_dog_name_adopt;
        TextView tv_dog_type_adopt;
        TextView tv_shelter_name_adopt;
        TextView tv_date_adopt;
        TextView tv_status_adopt;
        ImageView dog_pic;
    }

    public AdoptionRecordAdapter(ArrayList<AdoptionRecord> data, Context context) {
        super(context, R.layout.adoption_record_listitem, data);
        this.dataSet = data;
        this.mContext=context;

    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        // Get the data item for this position
        AdoptionRecord adoptionRecord = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if ( convertView == null)
        {
            ///////////
            //tv_dog_name_adopt;
            //tv_dog_type_adopt;
            //tv_shelter_name_adopt;
            //tv_date_adopt;
            //tv_status_adopt;
            //dog_pic;
            //////////////////

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.adoption_record_listitem, parent, false);
            viewHolder.tv_dog_name_adopt = (TextView) convertView.findViewById(R.id.dog_name_adopt);
            viewHolder.tv_dog_type_adopt = (TextView) convertView.findViewById(R.id.dog_type_adopt);
            viewHolder.tv_shelter_name_adopt = (TextView) convertView.findViewById(R.id.shelter_name_adopt);
            viewHolder.tv_date_adopt = (TextView) convertView.findViewById(R.id.date_adopt);
            viewHolder.tv_status_adopt = (TextView) convertView.findViewById(R.id.status_adopt);
            viewHolder.dog_pic = (ImageView) convertView.findViewById(R.id.dog_adopted_image);



            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        lastPosition = position;

        ///////////
        //tv_dog_name_adopt;
        //tv_dog_type_adopt;
        //tv_shelter_name_adopt;
        //tv_date_adopt;
        //tv_status_adopt;
        //dog_pic;
        //////////////////

        viewHolder.tv_dog_name_adopt.setText(adoptionRecord.getDog_name());
        viewHolder.tv_dog_type_adopt.setText(adoptionRecord.getDog_type());
        viewHolder.tv_shelter_name_adopt.setText(adoptionRecord.getShelter_name());
        viewHolder.tv_date_adopt.setText(adoptionRecord.getDate());
        viewHolder.tv_status_adopt.setText(adoptionRecord.getStatus());
        viewHolder.dog_pic.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
